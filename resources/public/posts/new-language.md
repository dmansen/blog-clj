### What I Want From A Language

This is the first in (hopefully) many more articles detailing my path in designing and implementing a new language. The focus of this entry will be to focus on what features I like from various languages, and how I might be able to combine them to create a language I enjoy using. But first, I should answer the question:

#### Why another language?

My number one reason for designing and implementing a language is because I think it will be fun and educational. If I get something that I or other people enjoy using, that would be a nice bonus. I also have never done this before, so I expect to make a lot of mistakes along the way. Perhaps I can use those to guide the design of my next language.

With that question answered, I'll try to cover the highlights: things I would like to see in a new language.

#### Primary model

##### Functional

I'm a big proponent of functional languages. My favorite thing about Haskell is that everything is a function, because that means I can understand any Haskell code I read in terms of functions. I don't think I want to allow other kinds of syntax.

##### Functional, with structs

Haskell's record syntax is a known wart in the language. Algebraic datatypes are nice, but often you want a larger container to hold data. In my opinion, objects are not the answer: instead, create data structures and functions to operate on them (it achieves the same purpose).

##### Strong typing

I don't want to elaborate on this much, but I really don't want to allow any automatic type coercion. It only causes problems.

##### Static typing with inference

There are plenty of dynamic languages to choose from. While they are fun, I don't think they leverage the power of computers enough to last far into the future. I want the compiler to do as much work as possible up front; therefore, strong static typing is a must.

##### Typeclasses and polymorphic functions

This would be borrowed almost entirely from Haskell. It would make dealing with structs / functions instead of objects bearable.

##### Strict semantics

While lazy evaluation allows you to do extremely cool and flashy things, I don't think it buys you much in terms of developer efficiency and readability. It's just as easy to wrap something in a lambda or a future to emulate laziness when necessary, so I won't go to the trouble of implementing it directly in the language. This also means that you'll be able to do IO in any context; I might consider adding some sort of "pure" keyword to distinguish functions which don't perform any IO.

##### Small language core

I'm a fan of Lisp and Haskell. Both languages have a relatively small core, and the rest of the languages is built-up from those small building blocks. Besides making it easier for me to implement, this also means developers need to remember less when they learn the language.

##### (Probably) ML-like syntax

This seems like the best way to go. I like Lisp, but I don't think we need another dialect of it (Clojure and Racket should be more than enough for anyone). One Haskell feature I can't live without anymore is auto-currying, so that'll definitely be included.

#### Essential non-language features

I view some features as absolutely essential for me to use any language. Here are some of them.

##### Intepreted + Compiled

There's no reason not to have a rich REPL along with compilation for speed these days. I would hate to use a language without them.

##### (Somewhat) Backend-agnostic

Some languages, like Scala and Clojure, specifically tie themselves to the JVM instead of any other platform. This has its advantages (speed and ubiquity being the most important), but since I'm not really looking to bust into the mainstream with this I'd like to implement it in something I enjoy (not Java). LLVM could be a prime target for optimization. Despite my dislike of the JVM, I'd like to keep it open as an option, so I want to try to keep that part of the code modular.

##### A rich module system

I've heard great things about the ML family of languages module systems. A good module system keeps you from going crazy, and allows you to create modular code for yourself (this is even more imporant in libraries).

#### Disallowed features

I think these features cause trouble in other languages, so I won't allow them.

##### Easy creation of DSLs

I'm not a fan of DSLs that don't look like the language they're written in. I understand their value, but I just hate looking at a new bit of code and having to re-learn the semantics of a language which isn't the one I'm writing in. It takes too much time and is usually unnecessary.

##### Operator soup

Haskell and Scala both allow you to use any symbol as an operator. This has its advantages. It also leads to horrible operator soup, which tends to have the same effect as these non-language DSLs. This should be discouraged, but I'm not entirely sure I want to rule it out completely just yet - maybe I can find a middle ground.

#### Final thoughts

My main design goals are to end up with readable programs. Developer productivity is a close second, but I never want to sacrifice readability by creating shortcuts. This is just my first day thinking about this, so I may change my mind soon (or once I start implementing it). I'll continue to write as the ideas develop.
