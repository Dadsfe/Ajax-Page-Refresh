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


class VideoPlayer {
  val ids=List("Yh0c1US-Yjs","-yh6SriAjdE","kaFdB1W_P_I" )
  var current=0
  
  
  // replace the contents of the element with id "time" with the date
  def render = {
    def skip(amount:Int) : JsCmd =
	{
	  if (current+amount < ids.length && current + amount >= 0 )
	  {
	    current+=amount
		return Run("ytplayer.loadVideoById(\""+ids(current)+"\")")  & SetHtml("videoID",Text(current+"")) 
	  }
	  else
	  {
	    S.error("playlist","End of the playlist is reached") 
		return Noop
	  }
	}
	
	"#vp-script" #> <head>{Script(JsRaw("""
		ytplayer=null;
		function onYouTubePlayerReady(playerId) {
		  ytplayer = document.getElementById("myytplayer");
		  ytplayer.cueVideoById("Yh0c1US-Yjs");
		}
		$(document).ready(function(){
			var params = { allowScriptAccess: "always" };
			var atts = { id: "myytplayer" };
			swfobject.embedSWF("http://www.youtube.com/apiplayer?enablejsapi=1&version=3",
			"ytapiplayer", "100", "80", "8", null, null, params, atts);
		});
	""")) }</head> &
	"@currentID" #> SHtml.hidden(()=> Noop, "value" -> (""+current)) & 
	"@nextButton" #> (SHtml.ajaxButton("Next",()=> skip(1) )) &
	"@prevButton" #> (SHtml.ajaxButton("Previous",()=> skip(-1) ))  &
	"@playpauseButton" #> SHtml.submit("Pause", ()=>Noop , "onclick" -> JsRaw("""
		if (ytplayer.getPlayerState()==1) 
		{
			ytplayer.pauseVideo(); 
			$('#playpauseButton').attr("value","Play");
		}
		else {
			ytplayer.playVideo(); 
			$('#playpauseButton').attr("value","Pause");
		}
	 """).toJsCmd)
	
  }
}

