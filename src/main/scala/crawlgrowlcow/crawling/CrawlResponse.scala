package scala.crawlgrowlcow.crawling

import org.json4s.JsonAST._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.jsoup.Jsoup
import org.json4s.JsonDSL._

import scala.xml.XML

  /**
    * Created by art2rik1 on 21.11.16.
    */
  class CrawlResponse(
                            request: CrawlRequest,
                            status: Int,
                            headers: Map[String, IndexedSeq[String]],
                            body: String,
                            created: Long = System.currentTimeMillis,
                            timeTaken: Int = 1
                          ) {
    def toDom = Jsoup.parse(body)

    def toJson = parse(body).asInstanceOf[JObject]

    def toXml = XML.loadString(body)

    def toJsonString = compact(render(("request" -> request.toJson) ~
      ("status" -> status) ~
      ("response" -> body) ~
      ("created" -> created) ~
      ("timeTaken" -> timeTaken)))
  }


