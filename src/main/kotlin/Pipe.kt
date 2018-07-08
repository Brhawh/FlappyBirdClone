import processing.core.PApplet

/**
 * Created by Benn on 30/06/2018.
 */
class Pipe(private val applet: PApplet) {

    private var xPos = applet.width.toFloat()
    var topEnd = 0f
    private val moveSpeed = 5f
    private val width = 100f
    private val gapSize = 200f

    init {

        topEnd = applet.random(10f, applet.height - gapSize - 10f)
    }

    fun show() {
        applet.rect(xPos, 0f, width, topEnd)
        val bottomPipeHeight = applet.height - topEnd - gapSize
        applet.rect(xPos, topEnd + gapSize, width, bottomPipeHeight)
    }

    fun update() {
        xPos -= moveSpeed
    }

    fun isOffScreen(): Boolean {
        return xPos < -width
    }

    fun isColliding(bird: Bird): Boolean {
        return birdWithinCollidingRange(bird) && (birdCollidingWithTop(bird) || birdCollidingWithBottom(bird))
    }

    private fun birdCollidingWithBottom(bird: Bird): Boolean {
        return bird.yPos > topEnd + gapSize - bird.height / 2
    }

    private fun birdCollidingWithTop(bird: Bird): Boolean {
        return bird.yPos < topEnd + bird.height / 2
    }

    private fun  birdWithinCollidingRange(bird: Bird): Boolean {
        return (xPos - bird.xPos < bird.width / 2) && (xPos + width - bird.xPos > -bird.width)
    }

    var pointCollected: Boolean = false

    fun shouldScorePoint(bird: Bird): Boolean {
        return !pointCollected && (bird.xPos - bird.width / 2) - (xPos + width)  > 0
    }
}