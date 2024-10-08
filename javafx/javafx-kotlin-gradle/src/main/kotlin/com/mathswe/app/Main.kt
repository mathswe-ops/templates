package com.mathswe.app

import javafx.application.Application
import javafx.application.Application.*
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.VBox
import javafx.stage.Stage

class Main: Application() {
    override fun start(primaryStage: Stage) {
        val root = VBox()
        val scene = Scene(root, 600.0, 400.0)
        val btn = Button()

        btn.text = "Hello World"
        btn.setOnAction { actionEvent -> println("Hello World") }

        root.alignment = Pos.CENTER
        root.children
            .add(btn)

        primaryStage.title = "Slides"
        primaryStage.scene = scene
        primaryStage.show()
    }
}

fun main() {
    launch(Main::class.java)
}

// Function test
fun sum(a: Int, b: Int): Int = a + b
