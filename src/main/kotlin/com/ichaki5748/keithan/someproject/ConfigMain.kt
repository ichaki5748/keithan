package com.ichaki5748.keithan.someproject

import com.ichaki5748.keithan.Environment
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngineFactory
import javax.script.ScriptEngine

object ConfigMain {

  @JvmStatic
  fun main(args: Array<String>) {

    val scriptEngine: ScriptEngine = KotlinJsr223JvmLocalScriptEngineFactory().scriptEngine

    val script = javaClass.getResource("/dev.kts").readText(Charsets.UTF_8)

    val env: Environment = scriptEngine.eval(script) as Environment

    println(env)
  }

}