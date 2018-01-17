/*
Name: Jose Salinas
Date: 11/12/2016
Tabs: 2

Program runs with command 
run.

There are three cannibals and three missionaries on one bank of a river.
They want to get to the other side. There is a rowboat, but it can only
hold one or two people at a time. The boat will not go across the river
by itself. It must be rowed. Either a missionary a cannibal can row the
boat, but if it ever happens that cannibals outnumber missionaries on
either bank of the river, then the cannibals will eat the missionaries
on that bank. (Note that it is okay for there to be some positive number
of cannibals and no missionaries on a bank, since then there are no 
missionaries to be eaten.) Someone who is in the boat on a given bank
is considered to be on that bank, so a missionary cannot hide in the boat.
No state will occur twice. There are four solutions to this problem.
*/

/*
follows(S, T) that is true if state T can immediately follow state S in the
sequence of states. The change from S to T might involve a missionary and a
cannibal paddling across the river, for example. */

% One missionary crosses to the right bank
follows(state(leftBank([m | ML], CL, boat), rightBank(MR, CR, no_boat)),
state(leftBank(ML, CL, no_boat), rightBank([m | MR], CR, boat))).

% One cannibal crosses to the right bank
follows(state(leftBank(ML, [c | CL], boat), rightBank(MR, CR, no_boat)),
state(leftBank(ML, CL, no_boat), rightBank(MR, [c | CR], boat))).

% One missionary crosses to the left bank
follows(state(leftBank(ML, CL, no_boat), rightBank([m | MR], CR, boat)),
state(leftBank([m | ML], CL, boat), rightBank(MR, CR, no_boat))).

% One cannibal crosses to the left bank
follows(state(leftBank(ML, CL, no_boat), rightBank(MR, [c | CR], boat)),
state(leftBank(ML, [c | CL], boat), rightBank(MR, CR, no_boat))).

% Two cannibals cross to the right bank
follows(state(leftBank(ML, [c,c | CL], boat), rightBank(MR, CR, no_boat)),
state(leftBank(ML, CL, no_boat), rightBank(MR, [c,c | CR], boat))).

% Two missionaries cross to the right bank
follows(state(leftBank([m,m | ML], CL, boat), rightBank(MR, CR, no_boat)),
state(leftBank(ML, CL, no_boat), rightBank([m,m | MR], CR, boat))).

% Two cannibals cross to the left bank
follows(state(leftBank(ML, CL, no_boat), rightBank(MR, [c,c | CR], boat)),
state(leftBank(ML, [c,c | CL], boat), rightBank(MR, CR, no_boat))).

% Two missionaries cross to the left bank
follows(state(leftBank(ML, CL, no_boat), rightBank([m,m | MR], CR, boat)),
state(leftBank([m,m | ML], CL, boat), rightBank(MR, CR, no_boat))).

% One missionary and one cannibal cross to the right bank
follows(state(leftBank([m | ML], [c | CL], boat), rightBank(MR, CR, no_boat)),
state(leftBank(ML, CL, no_boat), rightBank([m | MR], [c | CR], boat))).

% One missionary and one cannibal cross to the left bank
follows(state(leftBank(ML, CL, no_boat), rightBank([m | MR], [c | CR], boat)),
state(leftBank([m | ML], [c | CL], boat), rightBank(MR, CR, no_boat))).

/* admissibleBank(Bank) is true if no missionary is being eaten at that Bank.
Mode: admissibleBank(in). It allows for 0 missionaries and a positive amount of
cannibals. The number of cannibals cannot outnumber the amount of missionaries
when missionaries is not 0. The amount of missionaries can outnumber the amount of
cannibals. */

admissibleBank(M, _) :- length(M, 0), !.
admissibleBank(M, C) :- length(M, X), length(C, Y), \+(X<Y).

/* This code works too but is longer.
admissibleBank(M, _) :- length(M, 0), !.
admissibleBank(M, C) :- length(M, 3), length(C, 3), !.
admissibleBank(M, C) :- length(M, 3), length(C, 2), !.
admissibleBank(M, C) :- length(M, 3), length(C, 1), !.
admissibleBank(M, C) :- length(M, 3), length(C, 0), !.
admissibleBank(M, C) :- length(M, 2), length(C, 2), !.
admissibleBank(M, C) :- length(M, 2), length(C, 1), !.
admissibleBank(M, C) :- length(M, 2), length(C, 0), !.
admissibleBank(M, C) :- length(M, 1), length(C, 1), !.
admissibleBank(M, C) :- length(M, 1), length(C, 0), !.
*/

/* admissible(S) is true if nothing is being eaten on either bank in state S.
Mode: admissible(in). */
admissible(state(leftBank(ML, CL, _), rightBank(MR, CR, _))) :-
admissibleBank(ML, CL), admissibleBank(MR, CR).

/* lengthen(Sol, Longer) is true if Longer = [S | Sol] where S is an 
admissible state that does not belong to Sol and that can follow
the head of Sol. That is, Longer is a slightly longer partial solution.
Mode: lengthen(in,out). */
lengthen([St | Rest], [NewSt, St | Rest]) :-
	follows(St, NewSt),
	admissible(NewSt),
	\+(member(NewSt, [St | Rest])).

/* plan(X,G,L) takes a list of states X, a goal state G and a list of states L, and is 
true if L is an extension of X that begins with state G. That is, in terms
of the lists, L = Z ++ X and L = [G | Y]. for some lists Y and Z.
Mode: plan(in, in, out) */
plan([Goal | Rest], Goal, [Goal | Rest]).
plan(PartialSolution, Goal, FullSolution) :-
	lengthen(PartialSolution, LongerSolution),
	plan(LongerSolution, Goal, FullSolution).

showPlan([]).
showPlan([state(LEFT, RIGHT) | Rest]) :-
write(LEFT), write(' ----------- '), write(RIGHT), nl,nl, showPlan(Rest).

run :-
	Start = state(leftBank([m,m,m], [c,c,c], boat),rightBank([],[], no_boat)),
	End = state(leftBank([], [], no_boat),rightBank([m,m,m], [c,c,c], boat)),
	plan([Start], End, WholePlan),
	reverse(WholePlan, FwdSolution),
	showPlan(FwdSolution).
