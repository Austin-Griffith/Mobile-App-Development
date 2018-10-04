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

class GameScene: SKScene, SKPhysicsContactDelegate {
    
    var galaxyInSpace:SKEmitterNode!
    var player:SKSpriteNode!
    
    //SKLabelNodes used to display content to the game view
    var scoreBanner:SKLabelNode?

    var score:Int = 0 {
        didSet {
            scoreBanner?.text = "Score: \(score)"
            }
        }

    
    override func didMove(to view: SKView)
    {
        
        //loading the starfield background
        galaxyInSpace = SKEmitterNode(fileNamed: "Starfield")
        
        //declare position
        galaxyInSpace.position = CGPoint(x:0, y:1400)
        
        //speed up the simulation so no gap from blank to stars
        galaxyInSpace.advanceSimulationTime(12)
        
        //itinitizle the object to view on screen
        self.addChild(galaxyInSpace)
        
        galaxyInSpace.zPosition = -1
        
        player = SKSpriteNode(imageNamed: "spaceShip")
        
        //setting the position of the score label to top left conner of game frame
        player.position = CGPoint(x: self.frame.size.width / 9, y: player.size.height / 5 - 500 )
        self.addChild(player)
        
        //adding the gravity properties
        //adding gravity will pull down or push up in their direction
        self.physicsWorld.gravity = CGVector(dx: 0 , dy: 0)
        self.physicsWorld.contactDelegate = self
        
        
        scoreBanner = SKLabelNode(text: "Score: 0")
        scoreBanner?.position = CGPoint(x: self.frame.size.width / 15 - 250, y: self.size.height / 5 + 315 )
        scoreBanner?.fontName = "AmericanTypewriter-Bold"
        scoreBanner?.fontSize = 75
        scoreBanner?.fontColor = UIColor.red
        score = 0
        
        self.addChild(scoreBanner!)
        
        
        
        }
        

    
    override func update(_ currentTime: TimeInterval)
    {
        // Called before each frame is rendered
    }
    
    
}
