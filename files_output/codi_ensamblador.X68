	org $1000
START:
	JSR SCREENSIZE
* --> | GOTO | null | null | main |
	JMP main
* --> | SKIP | null | null | main |
main:
* --> | PMB | null | null | main |
* --> | ASIGNA | 5 | null | t0 |
	MOVE.W #5,t0_main
* --> | ASIGNA | t0 | null | in |
	MOVE.W t0_main,in_main
* --> | MULTIPLICACION | 0 | 3 | t2 |
	MOVE.W #0,D0
	EXT.L D0
	MOVE.W #3,D1
	EXT.L D1
	MULS.W D0,D1
	MOVE.W D1,t2_main
* --> | SUMA | t2 | 1 | t3 |
	MOVE.W t2_main,D0
	MOVE.W #1,D1
	JSR ISUMA
	MOVE.W D1,t3_main
* --> | RESTA | t3 | 0 | t4 |
	MOVE.W t3_main,D1
	MOVE.W #0,D0
	JSR IRESTA
	MOVE.W D1,t4_main
* --> | MULTIPLICACION | t4 | 4 | t5 |
	MOVE.W t4_main,D0
	EXT.L D0
	MOVE.W #4,D1
	EXT.L D1
	MULS.W D0,D1
	MOVE.W D1,t5_main
* --> | ASIGNA | in | t5 | array |
	LEA.l array_main,A0
        move.w t5_main,A1
        add.l A1,A0
        move.w in_main,D0
        MOVE.w D0,(A0)
* --> | MULTIPLICACION | 0 | 3 | t7 |
	MOVE.W #0,D0
	EXT.L D0
	MOVE.W #3,D1
	EXT.L D1
	MULS.W D0,D1
	MOVE.W D1,t7_main
* --> | SUMA | t7 | 1 | t8 |
	MOVE.W t7_main,D0
	MOVE.W #1,D1
	JSR ISUMA
	MOVE.W D1,t8_main
* --> | RESTA | t8 | 0 | t9 |
	MOVE.W t8_main,D1
	MOVE.W #0,D0
	JSR IRESTA
	MOVE.W D1,t9_main
* --> | MULTIPLICACION | t9 | 4 | t10 |
	MOVE.W t9_main,D0
	EXT.L D0
	MOVE.W #4,D1
	EXT.L D1
	MULS.W D0,D1
	MOVE.W D1,t10_main
* --> | ASIGNA | array | t10 | out |
        LEA.l array_main,A0
        move.w t10_main,A1
        add.l A1,A0
        move.w (A0),D0
        MOVE.w D0,out_main
* --> | PARAM_S | null | null | out |
	MOVE.W out_main,-(A7)
* --> | CALL | null | null | print |
	JSR IPRINT
	ADDA.L #2,A7
	SIMHALT
 
t0_main: DS.W 1
in_main: DS.W 1
t2_main: DS.W 1
t3_main: DS.W 1
t4_main: DS.W 1
t5_main: DS.W 1
array_main: DS.W 6
t7_main: DS.W 1
t8_main: DS.W 1
t9_main: DS.W 1
t10_main: DS.W 1
out_main: DS.W 1
 
strout: DS.B 100
	DC.W 0
 
SCREENSIZE:
	MOVE.L #1024*$10000+768,D1
	MOVE.B  #33,D0
	TRAP    #15
	RTS
ISUMA:
	BTST.L #15,D0
	BEQ ADD2
	NOT.W D0
	ADDQ.W #1,D0
	BTST.L #15,D1
	BEQ ADD1
	NOT.W D1
	ADDQ.W #1,D1
	ADD.W D0,D1
	NOT.W D1
	ADDQ.W #1,D1
	JSR ADD4
ADD1:
	SUB.W D0,D1
	JSR ADD4
ADD2:
	BTST.L #15,D1
	BEQ ADD3
	NOT.W D1
	ADDQ.W #1,D1
	SUB.W D1,D0
	MOVE.W  D0,D1
	JSR ADD4
ADD3:
	ADD.W D0,D1
ADD4:
	RTS
IRESTA:
	BTST.L #15,D1
	BEQ SUB2
	NOT.W D1
	ADDQ.W #1,D1
	BTST.L #15,D0
	BEQ SUB1
	NOT.W D0
	ADDQ.W #1,D0
	SUB.W D0,D1
	NOT.W D1
	ADDQ.W #1,D1
	RTS
SUB1:
	ADD.W D0,D1
	NOT.W D1
	ADDQ.W #1,D1
	RTS
SUB2:
	BTST.L #15,D0
	BEQ SUB3
	NOT.W D0
	ADDQ.W #1,D0
	ADD.W D0,D1
	RTS
SUB3:
	SUB.W D0,D1
	RTS
SPRINT:
	MOVE.L 4(A7),A1
	MOVE.L #1,D0
	MOVE.W #100,D1
	TRAP #15
	RTS
SLINE:
	MOVE.L 4(A7),A1
	MOVE.L A1,-(A7)
	JSR SPRINT
	ADDA.L #4,A7
	MOVE.L #11,D0
	MOVE.W #$00FF,D1
	TRAP #15
	ADD.W #1,D1
	AND.W #$00FF,D1
	TRAP #15
	RTS
IPRINT:
	CLR.L D1
	MOVE.W 4(A7),D1
	EXT.L D1
	MOVE.L #3,D0
	TRAP #15
	RTS
ILINE:
	MOVE.W 4(A7),D1
	MOVE.W D1,-(A7)
	JSR IPRINT
	ADDA.W #2,A7
	MOVE.L #11,D0
	MOVE.W #$00FF,D1
	TRAP #15
	ADD.W #1,D1
	AND.W #$00FF,D1
	TRAP #15
	RTS
GETINT:
	MOVE.L #4,D0
	TRAP #15
	MOVE.W D1,4(A7)
	RTS
GETSTR:
	MOVEA.L 4(A7),A1
	MOVE.L #2,D0
	TRAP #15
	RTS
STRCPY:
	MOVE.B (A0)+,(A1)+
	BNE STRCPY
	RTS
STRCMP:
	CMPM.B (A0)+,(A1)+
	BNE STRRET
	TST.B -1(A0)
	BNE STRCMP
STRRET:
	RTS
STRCON:
	CLR.W D0
	MOVE.W #100,D1
STRCON1:
	MOVE.B (A0)+,(A2)+
	BEQ STRCON2
	ADDQ.W #1,D0
	CMP.W D0,D1
	BEQ STRCON3
	JMP STRCON1
STRCON2:
	SUBA.L #1,A2
STRCON3:
	MOVE.B (A1)+,(A2)+
	BEQ STRCON4
	ADDQ.W #1,D0
	CMP.W D0,D1
	BEQ STRCON4
	JMP STRCON3
STRCON4:
	RTS
	END START
