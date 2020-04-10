# SCALA

## Premise

- Mathematical theories are without mutations of Identifiers
  > Theory of Polynomials define
  > `(a*x + b) + (c*x + d) = (a+c)*x + (b+d)`
  > It does not define an `OPERATOR TO CHANGE A COEFFIECIENT` while keeping the `PLOYNOMIAL THE SAME!`
  > ***`Every polynomial has a fixed Identity defined by coefficients but in Imperative programmng that identity is not STABLE since coefficients can be mutable in a given implementation`***

- `IDENTITY` needs to be preserved if you want your program to `OBSERVER` necessary laws in Mathematical theories

> `AVOID MUTATIONS AND FOCUS ON FUNCTIONS AS VALUES AND OPERATORS`

## Functions

- In a stricter sense FP means programming without `mutable variables`, `assignments`, `loops` and other imperative `control structures`
- Functions can be values that are `produced` `consumed` and `composed`

## functions in FP

- they can be defined anywhere
- they can be treated as a `VALUE`
- and for these `VALUE`s there exist set of operators

## Elements of Programming

- `primitive expressions` respresenting the simplest `elements`
- ways to `combine` expressions
- ways to `abstract` expressions - GIving a name to an `Expression`
  > Named Expression 2. **`def sumOfSquares(x: Double, y: Double) = Square(x) + Square(y)`**
  > Named Expression 1. **`def Square(x: Double) = x * x`**

## Function Application

- Eval all func arguments from left to right
- replace func application by func right hand side
- replace all formal parameters of the func by the actual arguments
  
### The Substitution Model

- Above scheme of evaluation is called substitution model
- Underlying Idea is `All evaluation does is Reduce an expression to a Value`
- This Model can be applied to all expressions till the time it has no side effects
- Substitution model is formalized in lambda calculus, which gives a foundation for functional programming
  
### Call-By-Name and Call-By-Value

- Both the strategies reduce the expression to the same final value as long as
  - The reduced expression consists of pure functions
  - both evaluations terminate
  
- Call-By-Value evaluates every function argument only once
- Call-By-Name evaluates the function argument only if it is used in the function's expression body

### What if Termination is not Guaranteed ?_

- if `CBV` evaluation of an expression `e` terminates then `CBN` evaluation of `e` terminates, too
- But other direction is not true

### SCALA normally uses call-by-value

- But if type of a function starts with `=>` ex `y: => Int` then for that argument CBN model used

## Conditionals and Value Definitions

- Condtional expressions ex `if-else`
  - def abs(x: Int) :Int = `if (x >=0) then x else -x`
  - x>=0 is a Predicate of type Boolean
  
> Boolean expressions:
> true false constants
> !b negation
> b && b conjunction
> b || b disjunction

### `Short-Circuit` evaluations

- `true && e` => `e`
- `false && e` => `false`
- `false` || `e` => `e`
- `true` || `e` => `true`

> Expressions and Values forget the Identifiers except when CBN
> Everything thing supposed to have a STABLE IDENTITY (NO MUTATIONS)
> IDENTITY = VALUE

### `val` form

- The right hand side of a `val` definition is evaluated at the point of the definition itself
- Afterwards the Name refers to the value
  > `val x = 2`
  > `val y = square(x)`

### Example

- def loop: Boolean = loop
- def x = loop >- this is CBN definition
- val x = loop >- this will not terminate

> Use `sbt new lampepfl/dotty.g8` to create new Scala project
> `sbt launchIDE`
> Using openJDK8 - steer away from ORACLE
