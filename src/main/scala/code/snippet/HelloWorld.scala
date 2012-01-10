package code 
package snippet 

import scala.xml.{NodeSeq, Text}
import net.liftweb.util._
import net.liftweb.common._
import java.util.Date
import code.lib._
import Helpers._
import net.liftweb._
import http._
import common._
import util.Helpers._
import js._
import JsCmds._
import JE._


class HelloWorld {
  lazy val date: Box[Date] = DependencyFactory.inject[Date] // inject the date
  var count=0

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> Text(count+"") &
	"#inc" #> SHtml.ajaxButton("Inc",()=>{count+=1; Run("$('#time').html('"+count+"');")}) 

  /*
   lazy val date: Date = DependencyFactory.time.vend // create the date via factory

   def howdy = "#time *" #> date.toString
   */
}

