;Jose Salinas
(define (eval-expr E env)
  (cond

    ;whether a value is a symbol
    ((symbol? E) (cadr(assoc E env)))
    
    ;This expression allow numbers and operations
    ; 1+1 = 2              2*7 = 14
    ; 7-2 = 5              6/2 = 3
    ((number? E) E)
    ((eqv? (car E) 'plus)  (apply + (eval-params (cdr E) env)))
    ((eqv? (car E) 'minus) (apply - (eval-params (cdr E) env)))
    ((eqv? (car E) 'times) (apply * (eval-params (cdr E) env)))
    ((eqv? (car E) 'divide) (apply / (eval-params (cdr E) env)))

    ;represent expression of the form (let (x v) e)
    ((eqv? (car E) 'let) (eval-expr (caddr E) (cons(list(caadr E) (eval-expr(cadadr E) env)) env)))

    ;left alone and evaluate
    ((eqv? (car E) 'closure) (eval-expr (caddr E) (cons (assoc (cadr E) env) (cadddr E))))

    ;represents a function in the form (closure x E env) 
    ((eqv? (car E) 'function) (list 'closure (cadr E) (caddr E) env))

    ;of the form (call F E) where F and E are expressions
    ((eqv? (car E) 'call) (eval-expr (caddr(eval-expr(cadr E)env)) (cons(list(cadr(eval-expr(cadr E)env))(eval-expr(caddr E)env))env)))

    ; If A = 0  it yields value of C
    ; If A is not 0 , it yields B
    ((eqv? (car E) 'if) (if (equal? (eval-expr (cadr E) env) 0) (eval-expr (cadddr E) env)(eval-expr (caddr E) env)))

    ; confused - return ()
    (else '())
  )
)

(define (eval-params E env)
   (if (null? E) '()
      (cons (eval-expr (car E) env) (eval-params (cdr E ) env))
   )
)
;Testing
(define (test)
(display "These are the test answers!")
(display "\n")
(display (eval-expr '(plus 5 5) '( )))
(display "\n")
(display (eval-expr '(times 4 8) '( )))
(display "\n")
(display (eval-expr '(minus 7 2) '( )))
(display "\n")
(display (eval-expr '(divide 6 2) '( )))
(display "\n")
(display (eval-expr '(plus (times 2 3) (minus 4 2)) '( )))
(display "\n")
(display (eval-expr '(plus y x) '((x 15)(y 10))))
(display "\n")
; testing for expression in the form ABC
(display (eval-expr '(if (plus 1 2) (minus 3 5) (plus 5 6)) '( )))
(display "\n")
(display (eval-expr '(if (times 2 4) (plus 1 8) (divide 5 0)) '( )))
(display "\n")
(display (eval-expr '(if (minus 5 5) (divide 1 0) (plus 1 8)) '( )))
(display "\n")
(display (eval-expr '(if (minus 24 24) (divide 56 0) (plus 5 4)) '( )))
(display "\n")
(display (eval-expr '(plus 2 3) '( )))
(display "\n")
(display (eval-expr 'y '((y 20))))
(display "\n")
(display (eval-expr 'mouse '((rabbit 4) (mouse 30) (hamster 25))))
(display "\n")
(display (eval-expr 'rabbit '((rabbit 4) (mouse 30) (hamster 25))))
(display "\n")
(display (eval-expr 'x '((y 2) (x 5))))
(display "\n")
(display (eval-expr '(times x 3) '((x 5) (y 2)))); = 15
(display "\n")
;checking let,funvtion,call
(display (eval-expr '(let (r (plus 2 5)) (times r 2)) '( )));14
(newline)
(display (eval-expr '(let (z 15) (plus z z)) '( )));30
(display "\n")
(display (eval-expr '(let (s 5) (let (t (plus s s)) (times s (plus t 3)))) '( )));65
(display "\n")
(display (eval-expr '(let (f(function z (times z (plus w 1))))(call f (times 2 a))) '((a 5) (w 20))))
(display "\n")
(display (eval-expr '(let (f(function z (times z (plus r 23))))(call f (times 2 a))) '((a 56) (r 434))))
(display "\n")
(display (eval-expr '(call (function z (plus z z)) (plus 5 3))'()))
(display "\n")
(display (eval-expr '(call (function z (plus z z)) (plus 343 56))'()))
)