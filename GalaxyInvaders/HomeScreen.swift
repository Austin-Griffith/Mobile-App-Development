//
//  HomeScreen.swift
//  GalaxyInvaders
//
//  Created by Austin Griffith on 11/28/18.
//  Copyright © 2018 Austin Griffith. All rights reserved.
//

import SpriteKit

class HomeScreen: SKScene {
    
    var galaxyBackground:SKEmitterNode!
    var startGameButton:SKSpriteNode!
    
    override func didMove(to view: SKView) {
        
        // linking the assets in HomeScreen.sks
        // using sprite kit scene to make the homescreen page
        galaxyBackground = self.childNode(withName: "GalaxyBackground") as! SKEmitterNode
        
        // starts the scrolling of the galaxayBackground 10 seconds early so no gaps in screen upon starting
        galaxyBackground.advanceSimulationTime(10)
        
        //withName: ""  is reffering to the name given in HomeScreen.sks
        startGameButton = self.childNode(withName: "StartGameButton") as! SKSpriteNode
        
        startGameButton.texture = SKTexture(imageNamed: "newGameButton" )
        
    }   //end of override didBegin function
    
    
    // need to override the touchesBegin function to not have the game immeditatley start upon app launch
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        
        let start = touches.first
        if let position = start?.location(in: self)
        {
            let gameArray = self.nodes(at: position)
            
            if gameArray.first?.name == "StartGameButton"
            {
                let homeScreenTransition = SKTransition.flipVertical(withDuration: 0.5)
                //let gameStartScene = GameScene(size: self.size)
                let gameStartScene = SKScene(fileNamed: "GameScene") as! GameScene
                gameStartScene.scaleMode = .aspectFill
                self.view?.presentScene(gameStartScene, transition: homeScreenTransition)
                
            }
            
            
            //Implementing Game Exit button here
            
//            let end = touches.first
//            if let position1 = start?.location(in: self)
//            {
//                if gameArray.last?.name == "EndGameButton"
//                {
//                    exit(0) ;
//                }
//            }
            
        }
        
    } // end of touchesBegan function
    

}   //end of HomeScreen class
