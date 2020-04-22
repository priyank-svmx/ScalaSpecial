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

### Blocks and Lexical Scoping

- Avoid name-space pollution
- A `block` is delimited by `Braces` `{}`
- `block`s are `expressions` in scala
- A `block` is a `sequence` of `definitions` or `expressions`
- the `last` element of a block is an `expression` that defines its value
- A `block` may appear anywhere an `expression` can

### Blocks and Visibilty

- The `definition`s inside a `block` are only _visible_ from within the `block`
- The `definitions` inside a `block` `shadow`definitions of the same names outside the `block`

> Expression Block - look at it as a BIGGER EXPRESSION where the `names`(VALUES) locally available, can be used in different `block` local `expressions`
> Can be viewed as `ONE BIG EXPRESSION` with some laws

## Application Re-writing Rule

- `def f(x1...xN) = B;`  =>  `f(v1,...vN)` => `[x1/v1, ..... xN/vN]B`
- `[x1/v1, ..... xN/vN]B` means the expression `B` in which all occurences of `x``i` are replaced by`v``i`
- `[x1/v1, ..... xN/vN]` is called a **`Substitution`**

> Try to view the `expression` `statically` - if a complex expression then after all the argument `evaluation` and `substitutions`, see it as static exploded expression which would reduce to a `VALUE` after applying computation laws between prior `VALUE`s
> `if-else` keeps the `exploded expression` simple to view - MENTAL MODEL
> view `Substitution` as how we solve mathematical equation on a paper - where `VALUE`s are pitted against each other on a static mmedium - PAPER - Similar viewone should have

### TAIL RECURSION

- If function calls it self as a `last` action, the function's stack frame can be re-used - which is an implementation detail and should not affect the MENTAL MODEL
- One should see it as after `EVAL` and `SUBSTITUTION` what is left in the `recursive` `expression` after few steps.
  - If the length of this `expression` is growing then we have not properly designed our expression to be Tail recursive
- `Tail recursive` functions are `Iterative`

<hr/>

## Higher Order Functions

- Functions that take other functions as `parameters` or that `return` `functions` as result are called higher order functions

### Type of a function

- `A => B` is the type of a `function` that takes an argument of Type `A` and returns a result of Type `B`

### Anonymous functions OR function literals

- `(x: Int, y: Int) => x + y`
  
### Anonymous functions are Syntactic Sugar

- Anonymous function `(x1: T1, x2: T2, x3: T3, ......xN: Tn) => E`
  - can always be expressed as `{def f (x1: T1, x2: T2, x3: T3, ......xN: Tn) = E;f}`
  
### Currying

- `sum(cube) (1,10) == (sum(cube))(1,10)`

```scala
def sum(f: Int => Int)(a: Int, b: Int) =
if(a > b) 0 else f(a) + sum(f)(a+1,b)

```

- In general, a definition of a function with multiple parameter lists
  - `def f(args1)(args2)(args3)...(argsN) = E`
  - where `N > 1` is equivalent to
  
```scala
def f(args1)(args2)(args3).. (args.N-1) = {def g(args.N) = E;g}
```

> Or for short

```scala
def f(args1)(args2)(args3).. (args.N-1) = (args.N => E)
```

#### Expansion of Multiple Parameter Lists

- By repeating the above process `N` times on

```scala
def f(args1)(args2)(args3)(args.N-1)(args.N) = E
```

> TO

```scala
def f = (args1 => (args2 => ...(args.N => E)))
```

> This style of definition is called `Currying`
> `functional` `types` associate to the right
>
> ***It is as good as solving Equations and veiw FP as system of Equations / EXpressions***

<hr/>

## Context-Free Syntax  in Extended Backus-Naur form(EBNF)

- `|` denotes an alternative
- `[...]` an option (0 or 1)
- `{...}` a repetition (0 or more)

### Types

- `Type` = `SimpleType` | `FunctionType`
  - `FunctionType` = `SimpleType` => `Type` | `([Types]) => Type`
- `SimpleType` = `Ident`
- `Types` = `Type` `{',' Type}`

### A `type` can be

- A `numeric type`: `Int`, `Double` (and `Byte`, `Short`, `Char`, `Long`, `Float`)
- The `Boolean` type with the values `true` and `false`
- The `string` type,
- A `function type`, like `Int => Int`, `(Int,Int)=> Int`

### Expressions

- `Expr` = `InfixExpr` | `FunctionExpr` | `if (Expr) Expr else Expr`
- `InfixExpr` = `PrefixExpr` | `InfixExpr` **Operator** `InfixExpr`
- `Operator` = `ident`
- `PrefixExpr` = ['+' | `-` | `!` | `~`] `SimpleExpr`
- `SimpleExpr` = `ident` | `literal` | `SimpleExpr` `.` `ident` | `Block`
- `FunctionExpr` = `Bindings` `=>` `Expr`

### Definition

- A `function-Definition` , like `def Square(x: Int) = x*x`
- A `value definition`, like `val y = Square(2)`

### Parameter

- call-by-value parameter
- call-by-name parameter

<hr/>

## Functions and Data

- `class` helps define new `type`
- `class` gives a `constructor` to define the new `type`

```scala
class Rational(x: Int, y: Int){
  def numer = x
  def denom = y
}

new Rational(1,2)
```

### Data Abstraction

- This ability to choose `different` implementations of the `data` without affecting `clients` is called `Data Abstraction`

### Access Modifier

- `Private` expressions and values only visible within the block
- `Public` expression and values are visible outside the block  

### Classes and Substitution

- `class C(x1,....x.M){...def f(y1,.....,y.N) = b ...}`
  > Question - How the following exphression evaluated ?
  > `new C(v1,...v.M).f(w1,...,w.N)`
  > There are three substitution at play
    > `[w1/y1,.....w.N/y.N][v1/x1,..., v.M/x.M][new C(v1,...v.M) / this]b`

- Infix Notation:
  - `r.add(s)` => `r add s`
- Relaxed Identifiers
  - Alphanumeric, Symbolic
- `_` underscore counts as a letter
- Alphanumeric Identifiers can also end in an underscore, followed by operators
- `unary` operators can be defined for a `class` ( `class` model of `scala`) `def unary_ - : Rational = ...`

#### Operator Precedence - user defined operators

- The `precedence` of an `operator` is determined by its `first` `character`
  - `[special characters]` > `[*, /, %,]` > `[+, _]` > `:` > `[!, =]` > `[< >]` > `[&]` > `^` > `|` > `[all letters]`
  - `a + b ^? c ?^ d less a ==> b| c` => `(a + b) ^? (c ?^ d) less a ==> b| c`
    - **check operator's first character**
  
> Try to view Classes as Expressions with a Namespace - all what `new` does is creates a fresh
> `Identity` for an expression - but then same `laws` of `expression` apply

## NOTE

> Classes are just different rules to reach an expression - Structure of Symbols and Identifiers
> Cannot mutate a structure - because STRUCTURES DON'T CONTAIN BUT CONNECT called persistent data structures

- `Base Class`es and `Super Class`es
  - if a `Class` doesn't `extend` any then engine assumes standard `java.lang`s `Object` class is assumed
  - The direct and in-direct `Super Class`es of `Class` `C` are called _`Base Classes`_

### Use `override` to redifine an existing , non-abstract definition in a `Base Class` to new definition in a `Sub Class`

## Object definition VS Class Definition

- This defines a `singleton` `object` named `Empty`
- No new instances of `Empty` can be created
- `Singleton` objects are values, so `Empty` evaluates to itself

```scala
object Empty extends IntSet {
  def contains(x: Int): Boolean = false
  def incl(x : Int): IntSet = new NonEmpty(x, new Empty, new Empty)
  override def toString = " EMPTY-SET "
}

```

## Dynamic Method Dispatch

- `Code` invoked by the `Method-call` depends on the `runtime-type` of the `object` that contains the `method`

## Packages

- Classes and Objects are organized in packages

```scala
package kill.drill

object hello{}

> scala kill.drill.hello
```

```scala
/*
 import just one entity
*/
import kill.drill.hello

/*
import everything
*/
import kill.drill._

/*Import some specifics*/

import kill.drill.{ hello, trello}

```

### Some Entities are automatically imported in any Scala Program

- All members of package `scala`
- All members of package `java.lang`
- All members of the singleton object `scala.Predef`

### Traits

- just like abstract classes

> Classes Objects and Traits can inherit from atmost one-class but multiple Traits

- `Traits` resemble intrefaces but are more powerful because they can contain `fields` and `concrete` methods
- `Traits` cannot have `value` parameters, pnly `Class`es can have those

### `Nothing`

- Is a sub-type of every type
- used to signal - abnormal termination and element type of empty collections
  
### Throw exception `new Error("sting message")`

### `Null` type

- Every refrence class `type` also has `null` as a value
- type of `null` is `Null`
- `Null` is a subtype of every class that `inherits` from object;
- it is INCOMPATIBLE with subtypes of `AnyVal` - like `Int`

## Polymorphism

- Immutable List
  - two building blocks `Nil` and `Cons` a structure pointing an element and rest of the `List`
- Polymorphism means that function `type` comes `in many forms`
  - The function can be applied to `arguments` of many `types`
  - The `type` can have instances of many `types`

### Two principal form of Polymorphism

- `subtyping` => instances of a `sub-class` can be passed to `base-class`
- `generics` => instances of a `function` or a `class` are created by `type parameterization`

## Type & Evaluation


- Type parameters do not affect `evaluation` in Scala
- All `type` parameters and `type` arguments are removed before evaluating the program
- Also called `type erasure`
  
