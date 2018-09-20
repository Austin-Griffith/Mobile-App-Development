//: Playground - noun: a place where people can play

import UIKit

var str = "Hello, playground"


////  OPTIONALS  ////

//var score : Int?
////score = 80
//
////print("Score is \(score)")
//
//print(score!)
//
////print("Score is \(score!)")
//
//if score != nil {
//    print("The score is \(score!)")
//}
//
//if let currentScore = score {
//    print("My current score is \(currentScore)")
//}
//
//var newScore : Int! = 95
//newScore = nil
//print("My new score is \(newScore)")
//
//var finalScore : Int

//finalScore = nil  //error



////  CLASSES  ///


class Vehicle {
    var wheelNum = 4
    var speed = 55
    var mpg = 20
    var tankCapacity = 20
    var name : String?
    func changeSpeed(amount : Int) {
        speed = speed + amount
    }
    func changeEfficiency(newSpeed: Int , newMpg: Int) {
        speed = newSpeed
        mpg = newMpg
    }
    //  initializers  //
    init(vehicleName vname : String) {
        name = vname
    }
    init() {}
}

let myJeep = Vehicle()
myJeep.mpg
myJeep.speed
myJeep.name
myJeep.changeSpeed(amount: 10)
myJeep.speed
myJeep.changeEfficiency(newSpeed: 35, newMpg: 25)

let myHybrid = Vehicle(vehicleName: "Prius")
myHybrid.name

if (myHybrid.name != nil) {
    print(myHybrid.name!)
}

myHybrid.speed


///  Inheritance  ///

class Bicycle : Vehicle {
    var reflectors : Bool?
    
    init(_ ref: Bool) {
        reflectors=ref
        super.init()
    }
    
    override init() {
        // do something interesting here //
        super.init()
    }
}


var bike = Bicycle()
bike.wheelNum
bike.wheelNum = 2
bike.wheelNum
myJeep.wheelNum
bike.reflectors
bike.reflectors = true
bike.reflectors

//myJeep.reflectors
//gives an error because Vehcile has no member 'reflectors'


///  classes are refereence types  ///

var newBike = bike
newBike.reflectors
newBike.reflectors = false
newBike.reflectors
bike.reflectors


///  Structs are value types  //

struct Skateboard {
    var color : String
    var brand : String
}

var board = Skateboard(color: "black", brand: "active")
board.color
var new_board = board
new_board.color
new_board.color = "purple"
new_board.color
board.color


///   Arrays   ///

var myList = [String] ()
var shoppingList = ["eggs", "butter", "milk"]
print(shoppingList[0])
shoppingList.append("bread")

if shoppingList.isEmpty {
    print("There is nothing to buy at the store")
}
else {
    print("You need to buy \(shoppingList.count)" + " items")
}

let item = shoppingList.removeLast()
print(item)

shoppingList.insert("coffe", at: 0 )
let oldItems = shoppingList.remove(at: 1)


///  Dictionaries  ///

var newList=[String:String] ()

var classes=["4120":"Mobile App Development", "2200":"TAM Web"]

classes["4120"]
classes["2200"] = "MIT"
classes.count

classes.updateValue("Mobile App Dev", forKey: "4120")
classes["4120"]
classes.removeValue(forKey: "2200")
classes.count
















