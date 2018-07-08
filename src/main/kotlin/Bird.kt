import processing.core.PApplet

/**
 * Created by Benn on 30/06/2018.
 */
class Bird(val applet: PApplet) {

    var xPos = 0f
    var yPos = 0f
    val height = 50f
    val width = 50f
    var velocity = 0f
    val gravity = 0.9f
    val lift = 25f

    fun update() {
        velocity += gravity
        velocity *= 0.9f
        yPos += velocity

        if (yPos + height/2 > applet.height) {
            yPos = applet.height - height/2
            velocity = 0f
        }

        if (yPos - height/2 < 0) {
            yPos = 0 + height/2
            velocity = 0f
        }
    }

    fun show() {
        applet.ellipse(xPos, yPos, width, height)
    }

    fun up() {
        velocity -= lift
    }
}