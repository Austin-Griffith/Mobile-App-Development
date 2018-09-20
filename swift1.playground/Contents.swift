//: Playground - noun: a place where people can play

import UIKit

var str = "Hello, playground"
var message : String = "This is a message"

var textMessage : String
textMessage = "A text from my iphone is a text message"

let classMax : Int = 20

let phoneMax : String
phoneMax = "iPhone 8"

print( str + ". " + message + ". " + textMessage + " on my " + phoneMax)

let a = 100
let b = 0.12345
let c = Double(a) + b
print(c)


var age : Int
age = 20
var young = " you are very young"
var old = "you are very old"

//   IF  ELSE  STATEMENTS   //

if age < 21 {
    print(young)
}
else {
    print(old)
}


//  SWITCH STATEMENTS  //

age < 21 ? young : old
age = 21

switch age{
case 0...5: print("youre just a little kid")
case 6..<21: print("Enjoy your teen years")
case 21...55: print("youre not a kid anymore get your shit together")
default: print("I dont know why you are still asking questions")
}

////  LOOPS  ////

for i in 0...age {
    print("\(i)")
}

// FUNCTIONS //

func sayHello() {
    print("hello world")
}

sayHello()


func sayHi(first: String , last: String) {
    print("Hi \(first) \(last)")
}

sayHi(first: "Bill", last: "Clinton")

func sayWhere(_ place:String) {
    print(place)
}

sayWhere("Be Boulder")

func sayWho(firstName: String , lastName : String ) -> String {
    return "Who " + firstName + " " + lastName + " ?"
}

let msg2 = sayWho(firstName: "John", lastName: "Doe")
print(msg2)

















