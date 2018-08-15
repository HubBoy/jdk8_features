package com.owl.jdk8.nashorn.javascrip;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

/**
 * Nashorn javascript引擎
 * 运行特定的javascript应用
 * 实现javascript与java的交互
 */
public class Js {
    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        System.out.println(engine.getClass().getName());
        System.out.println("Result:" + engine.eval("function f() { return 1; }; f() + 1;", new SimpleScriptContext()));

        /* jdk.nashorn.api.scripting.NashornScriptEngine
         * Result:2.0
         */
    }
}
