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
import scala.util.Random


class ajaxSurround {
  def render(in: NodeSeq) = {
	if(S.param("ajax")!="true")
		<div class="lift:surround?with=header;at=main-container">{in}</div>
	else
	{
		for {
			session <- S.session
		} { session.requestHtmlProperties.set(session.
			requestHtmlProperties.is.setDocType(() => 
			Full("<!DOCTYPE mush>")))
			println("doctype changed")
		}
		
		in
	}
  }
}

