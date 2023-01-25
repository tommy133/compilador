	org $1000
START:
	JSR SCREENSIZE
* --> | GOTO | null | null | main |
	JMP main
* --> | SKIP | null | null | main |
main:
* --> | PMB | null | null | main |
* --> | ASIGNA |  RESULTAT:  | null | res |
	LEA.L aux0,A0
	LEA.L res_main,A1
	JSR STRCPY
* --> | ASIGNA |   |  | null | separator |
	LEA.L aux1,A0
	LEA.L separator_main,A1
	JSR STRCPY
* --> | ASIGNA |  PROVA CONDICIONAL: | null | testcond |
	LEA.L aux2,A0
	LEA.L testcond_main,A1
	JSR STRCPY
* --> | ASIGNA |  PROVA BUCLE WHILE: | null | testwhile |
	LEA.L aux3,A0
	LEA.L testwhile_main,A1
	JSR STRCPY
* --> | ASIGNA |  PROVA BUCLE REPEAT UNTIL: | null | testrepeat |
	LEA.L aux4,A0
	LEA.L testrepeat_main,A1
	JSR STRCPY
* --> | ASIGNA |  PROVA ARRAY N-DIMENSIONAL: | null | testarrayn |
	LEA.L aux5,A0
	LEA.L testarrayn_main,A1
	JSR STRCPY
* --> | ASIGNA |  PROVA ARRAY SERIE | null | testarray1 |
	LEA.L aux6,A0
	LEA.L testarray1_main,A1
	JSR STRCPY
* --> | PARAM_C | null | null | testcond |
	MOVE.L #testcond_main,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | ASIGNA | true | null | enter |
	MOVE.B #-1,enter_main
* --> | PARAM_C | null | null | res |
	MOVE.L #res_main,-(A7)
* --> | CALL | null | null | print |
	JSR SPRINT
	ADDA.L #4,A7
* --> | IFDIFERENTE | enter | -1 | L1 |
	CLR.W D1
	MOVE.B enter_main,D1
	EXT.W D1
	MOVE.W #-1,D0
	CMP.W D0,D1
	BNE L1
* --> | SKIP | null | null | L0 |
L0:
* --> | PARAM_C | null | null | TRUE |
	MOVE.L #aux7,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | GOTO | null | null | L2 |
	JMP L2
* --> | SKIP | null | null | L1 |
L1:
* --> | PARAM_C | null | null | FALSE |
	MOVE.L #aux8,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | SKIP | null | null | L2 |
L2:
* --> | PARAM_C | null | null |  |
	MOVE.L #aux9,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | PARAM_C | null | null | testwhile |
	MOVE.L #testwhile_main,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | ASIGNA | 0 | null | n1 |
	MOVE.W #0,n1_main
* --> | ASIGNA | 10 | null | n2 |
	MOVE.W #10,n2_main
* --> | SKIP | null | null | L3 |
L3:
* --> | IFMAYOR | n1 | n2 | L5 |
	MOVE.W n1_main,D1
	MOVE.W n2_main,D0
	CMP.W D0,D1
	BGT L5
* --> | SKIP | null | null | L4 |
L4:
* --> | PARAM_S | null | null | n1 |
	MOVE.W n1_main,-(A7)
* --> | CALL | null | null | print |
	JSR IPRINT
	ADDA.L #2,A7
* --> | PARAM_C | null | null | separator |
	MOVE.L #separator_main,-(A7)
* --> | CALL | null | null | print |
	JSR SPRINT
	ADDA.L #4,A7
* --> | SUMA | n1 | 1 | t13 |
	MOVE.W n1_main,D0
	MOVE.W #1,D1
	JSR ISUMA
	MOVE.W D1,t13_main
* --> | ASIGNA | t13 | null | n1 |
	MOVE.W t13_main,n1_main
* --> | GOTO | null | null | L3 |
	JMP L3
* --> | SKIP | null | null | L5 |
L5:
* --> | PARAM_C | null | null |  |
	MOVE.L #aux9,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | PARAM_C | null | null |  |
	MOVE.L #aux9,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | PARAM_C | null | null | testrepeat |
	MOVE.L #testrepeat_main,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | ASIGNA | 0 | null | n1 |
	MOVE.W #0,n1_main
* --> | SKIP | null | null | L6 |
L6:
* --> | PARAM_S | null | null | n1 |
	MOVE.W n1_main,-(A7)
* --> | CALL | null | null | print |
	JSR IPRINT
	ADDA.L #2,A7
* --> | PARAM_C | null | null | separator |
	MOVE.L #separator_main,-(A7)
* --> | CALL | null | null | print |
	JSR SPRINT
	ADDA.L #4,A7
* --> | SUMA | n1 | 1 | t17 |
	MOVE.W n1_main,D0
	MOVE.W #1,D1
	JSR ISUMA
	MOVE.W D1,t17_main
* --> | ASIGNA | t17 | null | n1 |
	MOVE.W t17_main,n1_main
* --> | IFMENOR | n2 | n1 | L6 |
	MOVE.W n2_main,D1
	MOVE.W n1_main,D0
	CMP.W D0,D1
	BLT L6
* --> | SKIP | null | null | L9 |
L9:
* --> | IFMAYOR | i | n | L11 |
	MOVE.W i_main,D1
	MOVE.W n_main,D0
	CMP.W D0,D1
	BGT L11
* --> | SKIP | null | null | L10 |
L10:
* --> | RESTA | i | 0 | t55 |
	MOVE.W i_main,D1
	MOVE.W #0,D0
	JSR IRESTA
	MOVE.W D1,t55_main
* --> | DESPLAZAR_BITS | 2 | t55 | t56 |
	MOVE.W t55_main,D0
	EXT.L D0
	LSL.L #2,D0
	MOVE.W D0,t56_main
* --> | ASIGNA | i | t56 | series |
	LEA.L series_main,A0
	MOVE.W t56_main,A1
	ADD.L A1,A0
	MOVE.W i_main,D0
	MOVE.W D0,(A0)
* --> | SUMA | i | 1 | t58 |
	MOVE.W i_main,D0
	MOVE.W #1,D1
	JSR ISUMA
	MOVE.W D1,t58_main
* --> | ASIGNA | t58 | null | i |
	MOVE.W t58_main,i_main
* --> | GOTO | null | null | L9 |
	JMP L9
* --> | SKIP | null | null | L11 |
L11:
* --> | PARAM_C | null | null |  |
	MOVE.L #aux9,-(A7)
* --> | CALL | null | null | line |
	JSR SLINE
	ADDA.L #4,A7
* --> | ASIGNA | 0 | null | i |
	MOVE.W #0,i_main
* --> | SKIP | null | null | L12 |
L12:
* --> | IFMAYOR | i | n | L14 |
	MOVE.W i_main,D1
	MOVE.W n_main,D0
	CMP.W D0,D1
	BGT L14
* --> | SKIP | null | null | L13 |
L13:
* --> | RESTA | i | 0 | t62 |
	MOVE.W i_main,D1
	MOVE.W #0,D0
	JSR IRESTA
	MOVE.W D1,t62_main
* --> | DESPLAZAR_BITS | 2 | t62 | t63 |
	MOVE.W t62_main,D0
	EXT.L D0
	LSL.L #2,D0
	MOVE.W D0,t63_main
* --> | ASIGNA | series | t63 | out |
	LEA.L series_main,A0
	MOVE.W t63_main,A1
	ADD.L A1,A0
	MOVE.W (A0),D0
	MOVE.W D0,out_main
* --> | PARAM_S | null | null | out |
	MOVE.W out_main,-(A7)
* --> | CALL | null | null | print |
	JSR IPRINT
	ADDA.L #2,A7
* --> | PARAM_C | null | null | separator |
	MOVE.L #separator_main,-(A7)
* --> | CALL | null | null | print |
	JSR SPRINT
	ADDA.L #4,A7
* --> | SUMA | i | 1 | t65 |
	MOVE.W i_main,D0
	MOVE.W #1,D1
	JSR ISUMA
	MOVE.W D1,t65_main
* --> | ASIGNA | t65 | null | i |
	MOVE.W t65_main,i_main
* --> | GOTO | null | null | L12 |
	JMP L12
* --> | SKIP | null | null | L14 |
L14:
	SIMHALT
 
res_main: DS.B 100
separator_main: DS.B 100
testcond_main: DS.B 100
testwhile_main: DS.B 100
testrepeat_main: DS.B 100
testarrayn_main: DS.B 100
testarray1_main: DS.B 100
enter_main: DS.B 1
	DC.W 0
n1_main: DS.W 1
n2_main: DS.W 1
t13_main: DS.W 1
t17_main: DS.W 1
t19_main: DS.W 1
in1_main: DS.W 1
t21_main: DS.W 1
in2_main: DS.W 1
t23_main: DS.W 1
t24_main: DS.W 1
t25_main: DS.W 1
t26_main: DS.W 1
t27_main: DS.W 1
t28_main: DS.W 1
array_main: DS.W 18
t30_main: DS.W 1
t31_main: DS.W 1
t32_main: DS.W 1
t33_main: DS.W 1
t34_main: DS.W 1
t35_main: DS.W 1
t37_main: DS.W 1
t38_main: DS.W 1
t39_main: DS.W 1
t40_main: DS.W 1
t41_main: DS.W 1
t42_main: DS.W 1
out1_main: DS.W 1
t44_main: DS.W 1
t45_main: DS.W 1
t46_main: DS.W 1
t47_main: DS.W 1
t48_main: DS.W 1
t49_main: DS.W 1
out2_main: DS.W 1
t51_main: DS.W 1
i_main: DS.W 1
t53_main: DS.W 1
n_main: DS.W 1
t55_main: DS.W 1
t56_main: DS.W 1
series_main: DS.W 11
t58_main: DS.W 1
t62_main: DS.W 1
t63_main: DS.W 1
out_main: DS.W 1
t65_main: DS.W 1
 
aux0: DC.B ' RESULTAT: ',0
aux1: DC.B '  | ',0
aux2: DC.B ' PROVA CONDICIONAL:',0
aux3: DC.B ' PROVA BUCLE WHILE:',0
aux4: DC.B ' PROVA BUCLE REPEAT UNTIL:',0
aux5: DC.B ' PROVA ARRAY N-DIMENSIONAL:',0
aux6: DC.B ' PROVA ARRAY SERIE',0
aux7: DC.B 'TRUE',0
aux8: DC.B 'FALSE',0
aux9: DC.B '',0
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
