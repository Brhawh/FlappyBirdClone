import processing.core.PApplet

/**
 * Created by Benn on 30/06/2018.
 * Main processing applet.
 */
class FlappyBirdPApplet: PApplet() {

    lateinit var bird: Bird
    var pipes: Array<Pipe> = emptyArray()
    var score = 0

    override fun setup() {
        bird = Bird(this)
        bird.xPos = width / 6f
        bird.yPos = (height / 2) - (bird.height / 2)
    }

    override fun draw() {
        background(0)
        bird.update()
        for (pipe in pipes) {
            pipe.update()
            if (pipe.isColliding(bird)) {
                pipes = emptyArray()
                score = 0
            } else {
                if (pipe.shouldScorePoint(bird)) {
                    pipe.pointCollected = true
                    score++
                }
            }
        }
        pipes.filter(Pipe::isOffScreen)
        if (frameCount % 100 == 0) {
            pipes += Pipe(this)
        }
        fill(255)
        bird.show()
        for (pipe in pipes) pipe.show()
        fill(255f, 50f, 40f)
        text("Score: " + score, 10f, 10f)
    }

    override fun keyPressed() {
        super.keyPressed()
        if (key == ' ') bird.up()
    }

    companion object {
        fun run() {
            val pa = FlappyBirdPApplet()
            pa.setSize(1200, 800)
            pa.runSketch()
        }
    }
}

fun main(args: Array<String>) {
    FlappyBirdPApplet.run()
}