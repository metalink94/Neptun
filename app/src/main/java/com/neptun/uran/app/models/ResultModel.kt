package com.neptun.uran.app.models

class ResultModel(val firstTeam: String = "",
                  val secondTeam: String = "",
                  val firstScore: Int = 0,
                  val secondScore: Int = 0,
                  val coeff: String = ""): ViewModel

class TitleModel: ViewModel

class DividerModel: ViewModel

class TimeModel(val date: String = ""): ViewModel

interface ViewModel
