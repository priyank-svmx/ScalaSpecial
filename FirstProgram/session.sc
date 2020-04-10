0
def abs(x: Double) = if( x< 0.0) -x else x
def isGoodEnough (guess: Double, x: Double) = abs(guess * guess - x) < 0.001
def improve(guess: Double, x: Double) = (guess + x / guess ) / 2.0

def sqrIter(guess: Double, x: Double): Double = 
  if(isGoodEnough(guess,x)) guess else sqrIter(improve(guess, x),x)
def sqrt(x: Double): Double = sqrIter(1.0, x)

sqrt(2)