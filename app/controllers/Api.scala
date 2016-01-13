package controllers

import java.util.Date

import play.api.libs.json._
import play.api.mvc.{Action, Controller}
import play.api.libs.functional.syntax._

/**
  * Created by ndyumin on 13.01.2016.
  */
class Api extends Controller{

  case class Tournament(name: String, startDate: Date, endDate: Date, state: String)

  var list: List[Tournament] = {
    List(
      Tournament(
        "spring 2016",
        new Date(2016, 4, 13),
        new Date(2016, 4, 16),
        "scheduled"
      ),
      Tournament(
        "autumn 2015",
        new Date(2015, 9, 10),
        new Date(2016, 2, 10),
        "ongoing"
      ),
      Tournament(
        "autumn 2014",
        new Date(2014, 9, 10),
        new Date(2014, 9, 15),
        "finished"
      ),
      Tournament(
        "autumn 2013",
        new Date(2013, 9, 11),
        new Date(2013, 9, 12),
        "finished"
      )
    )
  }

  implicit val tournamentWrites: Writes[Tournament] = (
    (JsPath \ "name").write[String] and
    (JsPath \ "startDate").write[Date] and
    (JsPath \ "endDate").write[Date] and
      (JsPath \ "state").write[String]
    )(unlift(Tournament.unapply))

  def index = Action {
    Ok(views.html.api())
  }

  def getTournamentList = Action {
    Ok(Json.toJson(list))
  }
}
