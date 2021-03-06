package uselists

//import scala.annotation._ // wildcard, grab all
//import scala.annotation.{tailrec,... other stuff}
import scala.annotation.tailrec

object ListStuff {
  @tailrec
//  def showItems(l:List[_]) : Unit = l match {
//  def showItems[T](l:List[T]) : Unit = l match {
//    case Nil =>
//    case h :: t => println(s"> $h") ; showItems(t)
//  }
  def processItems[T](l:List[T])(op: T => Unit) : Unit = l match {
    case Nil =>
    case h :: t => op(h) ; processItems(t)(op)
  }

  // NOT TAIL RECURSIVE
  def map[T, U](l:List[T])(op: T => U): List[U] = l match {
    case Nil => Nil
    case h :: t => op(h) :: map(t)(op)
  }

  def main(args: Array[String]): Unit = {
    val names = List("Fred", "Jim", "Sheila")

    println(s"second item in list is ${names(1)}")
    println(s"first item in list is ${names.head}")
    println(s"names is $names")
    println(s"names tail is ${names.tail}")
    // List is structurally immutable

    //    val moreNames = "Alex" :: names
    val moreNames = names.::("Alex")

    println(s"more names is $moreNames")
    println(s"names is $names")

    val yetMore = "Freddy" :: "Bill" :: "Alan" :: "Susan" :: Nil

    /*val xxx = */yetMore match {
      case List(a, b, c) => println(s"a is $a, b is $b, c is $c")
      case h :: t => println(s"head is $h tail is $t")
      case _ => println("something else")
    }
    println("--------------------")
//    showItems(yetMore)
    processItems(yetMore)(x => println(s"lambda printing $x"))

//    processItems(map(yetMore)(x => x.toUpperCase))(y => println(s">> ${y}"))
    val doSomethingCool : String => Int = x => x.length
    processItems(map(yetMore)(doSomethingCool))(y => println(s">> ${y}"))
  }
}
