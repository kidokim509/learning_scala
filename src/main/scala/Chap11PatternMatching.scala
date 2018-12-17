/**
  * https://docs.scala-lang.org/tour/pattern-matching.html
  */

import scala.util.Random

object Chap11PatternMatching extends App {
  // Pattern matching is a mechanism for checking a value against a pattern.
  // It is a more powerful version of the switch statement in Java and it can likewise be used in place of a series of if/else statements.

  // Syntax
  val x: Int = Random.nextInt(10)

  x match {
    case 0 => "zero"
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }
  matchTest(3)  // many
  matchTest(1)  // one

  // Matching on case classes
  abstract class Notification
  case class Email(sender: String, title: String, body: String) extends Notification
  case class SMS(caller: String, message: String) extends Notification
  case class VoiceRecording(contactName: String, link: String) extends Notification

  def showNotification(notification: Notification): String = {
    notification match {
      case Email(email, title, _) =>
        s"You got an email from $email with title: $title"
      case SMS(number, message) =>
        s"You got an SMS from $number! Message: $message"
      case VoiceRecording(name, link) =>
        s"you received a Voice Recording from $name! Click the link to hear it: $link"

      // 그동안 다른 코드 보면서, 어차피 파라미터로 들어올 Object Pattern은 하나 밖에 없는데 왜 Pattern Matching을 썼을까
      // 싶은 코드들이 있었는데, 추측컨데 matching 하면서 object의 member를 자연스럽게 끄집어 낼 수 있고
      // 아래 처럼 Pattern Guard로 로직을 첨가할 수 있기 때문이 아니었을까?
    }
  }

  val someSms = SMS("12345", "Are you there?")
  val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")

  println(showNotification(someSms))  // prints You got an SMS from 12345! Message: Are you there?
  println(showNotification(someVoiceRecording))  // you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123

  // Pattern Guards
  // Pattern guards are simply boolean expressions which are used to make cases more specific. Just add if <boolean expression> after the pattern.
  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = {
    notification match {
      case Email(email, _, _) if importantPeopleInfo.contains(email) =>
        "You got an email from special someone!"
      case SMS(number, _) if importantPeopleInfo.contains(number) =>
        "You got an SMS from special someone!"
      case other =>
        showNotification(other) // nothing special, delegate to our original showNotification function
    }
  }

  val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")
  val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
  val importantSms = SMS("867-5309", "I'm here! Where are you?")

  println(showImportantNotification(someSms, importantPeopleInfo))
  println(showImportantNotification(someVoiceRecording, importantPeopleInfo))
  println(showImportantNotification(importantEmail, importantPeopleInfo))
  println(showImportantNotification(importantSms, importantPeopleInfo))

  // Matching on type only
  abstract class Device
  case class Phone(model: String) extends Device{
    def screenOff = "Turning screen off"
  }
  case class Computer(model: String) extends Device {
    def screenSaverOn = "Turning screen saver on..."
  }

  def goIdle(device: Device) = device match {
    case p: Phone => p.screenOff
    case c: Computer => c.screenSaverOn
  }

  // Sealed Classes
  // Traits and classes can be marked sealed which means all subtypes must be declared in the same file. This assures that all subtypes are known.
  sealed abstract class Furniture
  case class Couch() extends Furniture
  case class Chair() extends Furniture

  def findPlaceToSit(piece: Furniture): String = piece match {
    case a: Couch => "Lie on the couch"
    case b: Chair => "Sit on the chair"
  }
  // This is useful for pattern matching because we don’t need a “catch all” case.
  // -> sealed 한 파일에 다 subtype이 몰려있어서 모든 경우를 다 알고 있으므로
}
