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


//   UPDATES TO PROJECT   //
// get enemies to spawn from the sides of the screen //
// implement a home screen with button to start game //
// adding additional game timer for side enemy spawning //
// finish implementing the CoreMotion features --> controlling player object with phone tile //
// find collosions between player object and enemy objects


class GameScene: SKScene, SKPhysicsContactDelegate {
    
    
/// GAME properties ///
    var galaxyInSpace:SKEmitterNode!
    var player:SKSpriteNode!
    
    var gameTimer:Timer!
    var gameTimer1:Timer!
    
    var possibleEnemies = ["ufo1", "ufo3"]
    var possibleSideEnemies = ["ufo2", "ufo4"]
    
    //bit masks to calculate when an enemy is hit with a bullet from player
    var alienBitmask:UInt32 = 0x1 << 1
    var playerBitmask:UInt32 = 0x1 << 0
    
    //SKLabelNodes used to display content to the game view
    var scoreBanner:SKLabelNode?
    
    //keeping track of score from count of bulletDidCollideWithAlien function
    var score:Int = 0 {
        didSet {
            scoreBanner?.text = "Score: \(score)"
        }
    }
    
    
    // initialize variable for CoreMotion Library for tilt control of player
    var motionManager = CMMotionManager()
    var xMovement:CGFloat = 0
    

    
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
        player.position = CGPoint(x: self.frame.size.width / 10, y: player.size.height / 5 - 500 )

        
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
        scoreBanner?.fontColor = UIColor.green
        
        //initialize score to 0
        score = 0
        self.addChild(scoreBanner!)
        
        //calling addEnemy and AddSideEnemy within the gameTimer assignment values
        gameTimer = Timer.scheduledTimer(timeInterval: 0.75, target: self, selector: #selector(addEnemy), userInfo: nil, repeats: true)
        gameTimer1 = Timer.scheduledTimer(timeInterval: 0.75, target: self, selector: #selector(addSideEnemy), userInfo: nil, repeats: true)
        
        
        
        //motionManger used with acceleration to control tilting of phone to controlm ovement of the player object
        motionManager.accelerometerUpdateInterval = 0.2
        motionManager.startAccelerometerUpdates(to: OperationQueue.current!) { (data:CMAccelerometerData?, error:Error?) in

            if let accelerometerData = data {
                let acceleration = accelerometerData.acceleration
                self.xMovement = CGFloat(acceleration.x) * 0.75 + self.xMovement * 0.25
            }
        }
        
    } // end of didMove function
    
    
    //Performs any scene-specific updates that need to occur after physics simulations are performed
    override func didSimulatePhysics() {
        
        player.position.x += xMovement * 50
        if (player.position.x < -400)
        {
            player.position = CGPoint(x: self.size.width + 25, y: player.position.y )
        }
        else if (player.position.x > self.size.width + 25 )
        {
            player.position = CGPoint(x: -300, y: player.position.y )
            
        }
        
    }   //end of didSimulatePhysics
    
    
    
    
    @objc func addEnemy() {
        
        // using gameplay kit method arrayByShufflingObjects will randomly sort objects in the array when new one is added so random enemy is added
        possibleEnemies = GKRandomSource.sharedRandom().arrayByShufflingObjects(in: possibleEnemies) as! [String]
        
        //choose first position in the array of possibleEnemies
        let alien = SKSpriteNode(imageNamed: possibleEnemies[0])
        
        //distributes enemies between 0 and 414 coordinates
        let randomAlienPosition = GKRandomDistribution(lowestValue: 0, highestValue: 414)
        let position = CGFloat(randomAlienPosition.nextInt())
        
        //attaching values of position and physics body to alien object
        alien.position = CGPoint(x: position, y: self.frame.height + alien.size.height)
        
        //attaching values of position and physics body to alien object
        alien.physicsBody = SKPhysicsBody(rectangleOf: alien.size)
        alien.physicsBody?.isDynamic = true
        
        alien.physicsBody?.categoryBitMask = alienBitmask
        alien.physicsBody?.contactTestBitMask = playerBitmask
        alien.physicsBody?.collisionBitMask = 0
        
        self.addChild(alien)
        
        //initialize time for sponing enemies
        let animationDuration:TimeInterval = 4
        var actionArray = [SKAction]()
        
        actionArray.append(SKAction.move( to: CGPoint( x: position, y: -alien.size.height - 800 ), duration: animationDuration ) )
        actionArray.append(SKAction.removeFromParent())
        alien.run(SKAction.sequence(actionArray))
        
    }   //end of addEnemy function
    
    
    // method to spawn new enemies from the sides of the screen in game
    @objc func addSideEnemy() {
        
        possibleSideEnemies = GKRandomSource.sharedRandom().arrayByShufflingObjects(in: possibleSideEnemies) as! [String]
        //always will choose a random position in the array of possibleEnemies
        let alienSideSpawn = SKSpriteNode(imageNamed: possibleSideEnemies[0])
        
        //distributes enemies between 0 and 414 coordinates this is hard coded for 6plus and 7plus phone sizes
        let randomAlienPosition = GKRandomDistribution(lowestValue: 0, highestValue: 414)
        let position = CGFloat(randomAlienPosition.nextInt())
        
        //attaching values of position and physics body to alien object
        alienSideSpawn.position = CGPoint(x: (self.frame.height) + (alienSideSpawn.size.height), y: position)
        
        //making a rectangle around the object as its physics bounding area
        alienSideSpawn.physicsBody = SKPhysicsBody(rectangleOf: alienSideSpawn.size)
        alienSideSpawn.physicsBody?.isDynamic = true
        
        //using bitmask opperation with Unsign Int for collisions
        alienSideSpawn.physicsBody?.categoryBitMask = alienBitmask
        alienSideSpawn.physicsBody?.contactTestBitMask = playerBitmask
        alienSideSpawn.physicsBody?.collisionBitMask = 0
        
        //adding the enemy objects on each call to function
        self.addChild(alienSideSpawn)
        
        //initialize time for sponing enemies
        let animationDuration:TimeInterval = 5
        var actionArray = [SKAction]()
        
        actionArray.append(SKAction.move( to: CGPoint( x: -alienSideSpawn.size.height - 500, y: position ), duration: animationDuration ) )
        actionArray.append(SKAction.removeFromParent())
        alienSideSpawn.run(SKAction.sequence(actionArray))
        
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
        bulletNode.physicsBody?.categoryBitMask = playerBitmask
        bulletNode.physicsBody?.contactTestBitMask = alienBitmask
        bulletNode.physicsBody?.collisionBitMask = 0
        bulletNode.physicsBody?.usesPreciseCollisionDetection = true
        
        self.addChild(bulletNode)
        //setting speed of how long you see the bullet
        let animationDuration:TimeInterval = 0.8
        var actionArray = [SKAction]()
        
        actionArray.append(SKAction.move( to: CGPoint( x: player.position.x , y: self.size.height + 10 ), duration: animationDuration ) )
        actionArray.append(SKAction.removeFromParent())
        bulletNode.run(SKAction.sequence(actionArray))
        
    }   //END OF FIREBULLETS METHOD
    
    
    
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
        if (movingFirstBody.categoryBitMask & playerBitmask) != 0 && (movingSecondBody.categoryBitMask & alienBitmask) != 0  {
            bulletDidCollideWithAlien(bullet: movingFirstBody.node as! SKSpriteNode, alien: movingSecondBody.node as! SKSpriteNode)
        }
        
        //checking contact between the player and enemy
//        if (movingFirstBody.categoryBitMask & playerBitmask) != 0 && (movingSecondBody.categoryBitMask & alienBitmask) != 0 {
//            alienDidCollideWithPlayer(player: movingFirstBody.node as! SKSpriteNode, alien: movingSecondBody.node as! SKSpriteNode)
//        }
        
    }  // end of didBegin function
    
    
    
    
    
    func didCollide(_ contact: SKPhysicsContact) {
        
        print("INSIDE didCollide METHOD")
        
//        let playerNode = SKSpriteNode(imageNamed: "spaceShipBLue")
//        playerNode.position = player.position
//
//        //add physicbody to playerNode
//        playerNode.physicsBody = SKPhysicsBody(circleOfRadius: playerNode.size.width)
//        playerNode.physicsBody?.isDynamic = true
//        playerNode.physicsBody?.categoryBitMask = playerBitmask
//        playerNode.physicsBody?.contactTestBitMask = alienBitmask
//        playerNode.physicsBody?.collisionBitMask = 0
//        playerNode.physicsBody?.usesPreciseCollisionDetection = true
//
  
//        //physics bodies for player and enemy
//        var movingBody1:SKPhysicsBody
//        var movingBody2:SKPhysicsBody
//
//        //checking contact between bullet and enemy
//        if contact.bodyA.categoryBitMask < contact.bodyB.categoryBitMask
//        {
//            movingBody1 = contact.bodyA
//            movingBody2 = contact.bodyB
//        } else
//        {
//            movingBody1 = contact.bodyB
//            movingBody2 = contact.bodyA
//        }
//
        
        //bitwise & comparison to see which body is player and which body is the enemy
        //checking contact between the player and enemy
//                if (movingBody1.categoryBitMask & playerBitmask) != 0 && (movingBody2.categoryBitMask & alienBitmask) != 0 {
//                    alienDidCollideWithPlayer(player: movingBody1.node as! SKSpriteNode, alien: movingBody2.node as! SKSpriteNode)
//                }
        
    }  // end of didCollide function
    
    
    
    
 
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        //calling the fire bullets function when the user touches the screen
        fireBullets()
    }
    
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
        
        print("INSIDE bulletDidCollideWithPlayer METHOD")
        
        
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
            
            //removing object from screen
            explosion.removeFromParent()
        }
        
        //add to scroe when known an enemy is hit
        score += 1
        
    }
    
    
    
    func alienDidCollideWithPlayer(player: SKSpriteNode, alien: SKSpriteNode)  {
        
        //debug tests for method
        print("INSIDE alienDidCollideWithPlayer METHOD")
        
        
        //adding explosion feature when
        let explosion = SKEmitterNode(fileNamed: "bulletExplosion")!
        explosion.position = alien.position
        
        self.addChild(explosion)
        //adding sound to explosion when colloision happens
        self.run(SKAction.playSoundFileNamed("bigBang.mp3", waitForCompletion: false))
        
        alien.removeFromParent()
        
        //calls the SKAction with sound for 2 seconds
        self.run( SKAction.wait(forDuration: 2) ) {
            
            //removing object from screen
            explosion.removeFromParent()
        }
        
        //decrease to score when alien hits the player
        //score -= 5
        
    }
    

    
    override func update(_ currentTime: TimeInterval)
    {
        // Called before each frame is rendered
        //standard game loop rendering at 60 frames per second
    }
    
    
}
