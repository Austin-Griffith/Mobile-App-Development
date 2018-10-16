//
//  GameScene.swift
//  GalaxyInvaders
//
//  Created by Austin Griffith on 10/2/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import SpriteKit
//https://developer.apple.com/documentation/spritekit/

import GameplayKit
//https://developer.apple.com/documentation/gameplaykit

import CoreMotion
//https://developer.apple.com/documentation/coremotion

//  SOURCES USED IN PROJECT ///
//https://www.youtube.com/watch?v=Y38YvERodWQ
//https://www.youtube.com/watch?v=mvlwZs2ehLU
//https://www.youtube.com/watch?v=cJy61bOqQpg
//https://www.youtube.com/watch?v=hOYWSPjdyUw
//https://code.tutsplus.com/tutorials/create-space-invaders-with-swift-and-sprite-kit-introducing-sprite-kit--cms-23341



class GameScene: SKScene, SKPhysicsContactDelegate {
    
    
/// GAME properties ///
    var galaxyInSpace:SKEmitterNode!
    var player:SKSpriteNode!
    
    var gameTimer:Timer!
    var possibleEnemies = ["ufo1", "ufo3"]
    
    //bit masks to calculate when an enemy is hit with a bullet from player
    var alienCategory:UInt32 = 0x1 << 1
    var playerBulletCategory:UInt32 = 0x1 << 0
    
    //SKLabelNodes used to display content to the game view
    var scoreBanner:SKLabelNode?
    
    var motionManager = CMMotionManager()
    var xAcceleration:CGFloat = 0
    
    
    //keeping track of score from count of bulletDidCollideWithAlien function
    var score:Int = 0 {
        didSet {
            scoreBanner?.text = "Score: \(score)"
            }
    }

    
    override func didMove(to view: SKView)
    {
        
        //loading the starfield background
        galaxyInSpace = SKEmitterNode(fileNamed: "GalaxyBackground")
        
        //declare position
        galaxyInSpace.position = CGPoint(x:0, y:1000)
        
        //speed up the simulation so no gap from blank to stars
        galaxyInSpace.advanceSimulationTime(12)
        
        //itinitizle the object to view on screen
        self.addChild(galaxyInSpace)
        
        galaxyInSpace.zPosition = -1
        player = SKSpriteNode(imageNamed: "spaceShipBlue")
        
        //setting the position of the score label to top left conner of game frame
        player.position = CGPoint(x: self.frame.size.width / 9, y: player.size.height / 5 - 500 )
        self.addChild(player)
        
        //adding the gravity properties
        //adding gravity will pull down or push up in their direction
        self.physicsWorld.gravity = CGVector(dx: 0 , dy: 0)
        self.physicsWorld.contactDelegate = self
        
        //initialize the Score label to 0 and place it at the top left corner of the view
        scoreBanner = SKLabelNode(text: "Score: 0")
        scoreBanner?.position = CGPoint(x: self.frame.size.width / 15 - 250, y: self.size.height / 5 + 315 )
        scoreBanner?.fontName = "AmericanTypewriter-Bold"   //taken from apple UI fonts
        scoreBanner?.fontSize = 75
        scoreBanner?.fontColor = UIColor.red
        score = 0
        
        self.addChild(scoreBanner!)
        
        gameTimer = Timer.scheduledTimer(timeInterval: 0.75, target: self, selector: #selector(addEmeny), userInfo: nil, repeats: true)
        
//motionManger used with acceleration to control tilting of phone to controlm ovement of the player object
//        motionManager.accelerometerUpdateInterval = 0.2
//        motionManager.startAccelerometerUpdates(to: OperationQueue.current!) { (data:CMAccelerometerData?, error:Error?) in
//
//            if let accelerometerData = data {
//                let acceleration = accelerometerData.acceleration
//                self.xAcceleration = CGFloat(acceleration.x) * 0.75 + self.xAcceleration * 0.25
//            }
//        }
        
        } // end of didMove function
    
    @objc func addEmeny() {
        possibleEnemies = GKRandomSource.sharedRandom().arrayByShufflingObjects(in: possibleEnemies) as! [String]
        //always will choose a random position in the array of possibleEnemies
        let alien = SKSpriteNode(imageNamed: possibleEnemies[0])
        let randomAlienPosition = GKRandomDistribution(lowestValue: 0, highestValue: 414)
        let position = CGFloat(randomAlienPosition.nextInt())
        
        alien.position = CGPoint(x: position, y: self.frame.height + alien.size.height)
        alien.physicsBody = SKPhysicsBody(rectangleOf: alien.size)
        alien.physicsBody?.isDynamic = true
        
        alien.physicsBody?.categoryBitMask = alienCategory
        alien.physicsBody?.contactTestBitMask = playerBulletCategory
        alien.physicsBody?.collisionBitMask = 0
        
        self.addChild(alien)
        let animationDuration:TimeInterval = 3
        var actionArray = [SKAction]()
        
        actionArray.append(SKAction.move( to: CGPoint( x: position, y: -alien.size.height ), duration: animationDuration ) )
        actionArray.append(SKAction.removeFromParent())
        alien.run(SKAction.sequence(actionArray))
        
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        //calling the fire bullets function whent the user touches the screen
        fireBullets()
    }
    
    
    func fireBullets() {
        //adding game sound for fired bullet
        self.run(SKAction.playSoundFileNamed("laserFire.mp3", waitForCompletion: false))
        let bulletNode = SKSpriteNode(imageNamed: "bullet")
        bulletNode.position = player.position
        
        //moving bullet up from  initial position of player node
        bulletNode.position.y += 5
        
        //add physicbody to bullet
        bulletNode.physicsBody = SKPhysicsBody(circleOfRadius: bulletNode.size.width / 2)
        bulletNode.physicsBody?.isDynamic = true
        bulletNode.physicsBody?.categoryBitMask = playerBulletCategory
        bulletNode.physicsBody?.contactTestBitMask = alienCategory
        bulletNode.physicsBody?.collisionBitMask = 0
        bulletNode.physicsBody?.usesPreciseCollisionDetection = true
        
        self.addChild(bulletNode)
        //setting speed of how long you see the bullet
        let animationDuration:TimeInterval = 0.8
        var actionArray = [SKAction]()
        
        actionArray.append(SKAction.move( to: CGPoint( x: player.position.x , y: self.size.height + 10 ), duration: animationDuration ) )
        actionArray.append(SKAction.removeFromParent())
        bulletNode.run(SKAction.sequence(actionArray))
        
    }
    
    func didBegin(_ contact: SKPhysicsContact) {
        
        //physics bodies for player and enemy
        var movingFirstBody:SKPhysicsBody
        var movingSecondBody:SKPhysicsBody
        
        //checking contact between bullet and enemy
        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask
        {
            movingFirstBody = contact.bodyA
            movingSecondBody = contact.bodyB
        } else
        {
            movingFirstBody = contact.bodyB
            movingSecondBody = contact.bodyA
        }
        
        //bitwise & comparison to see which body is player and which body is the bullet
        if (movingFirstBody.categoryBitMask & playerBulletCategory) != 0 && (movingSecondBody.categoryBitMask & alienCategory) != 0  {
            bulletDidCollideWithAlien(bullet: movingFirstBody.node as! SKSpriteNode, alien: movingSecondBody.node as! SKSpriteNode)
            
        }
        
    }
    
//    override func didSimulatePhysics() {
//
//        player.position.x += xAcceleration * 50
//        if (player.position.x < -30)
//        {
//            player.position = CGPoint(x: self.size.width + 25, y: player.position.y )
//        }
//        else if (player.position.x > self.size.width + 25 )
//        {
//            player.position = CGPoint(x: -25, y: player.position.y )
//
//        }
//    }
    
    // enables touch dragging of the player object
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent?) {
        for touch: AnyObject in touches {
            let touchOfPoint = touch.location(in: self)
            let previousPointOfTouch = touch.previousLocation(in: self)
            let amountDragged = touchOfPoint.x - previousPointOfTouch.x
            player.position.x += amountDragged
        }
    }
    
    func bulletDidCollideWithAlien(bullet: SKSpriteNode, alien: SKSpriteNode)  {
        //adding explosion feature when
        let explosion = SKEmitterNode(fileNamed: "bulletExplosion")!
        explosion.position = alien.position
        
        self.addChild(explosion)
         //adding sound to explosion when colloision happens
        self.run(SKAction.playSoundFileNamed("bigBang.mp3", waitForCompletion: false))
        
        bullet.removeFromParent()
        alien.removeFromParent()
        
        //calls the SKAction with sound for 2 seconds
        self.run( SKAction.wait(forDuration: 2) ) {
            explosion.removeFromParent()
        }
        
        //add to scroe when known an enemy is hit
        score += 1
        
    }

    
    override func update(_ currentTime: TimeInterval)
    {
        // Called before each frame is rendered
        //standard game loop rendering at 60 frames per second
    }
    
    
}
