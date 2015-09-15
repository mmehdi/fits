;;(require* scpt/ordering)

;;;; Options exist

;;(deftemplate options (slot rqid) (slot optid) (slot fare (default 0)) (slot time (default 1)) (slot changes (default 0)) (slot evaluated (default FALSE)) )

;;(deftemplate ordering 
;;	(slot rqid 	(type STRING)	(default "none")) 
;;	(slot farew 	(type INTEGER)	(default 3)) 
;;	(slot timew 	(type INTEGER)	(default 2)) 
;;	(slot changesw 	(type INTEGER)	(default 1)) 
;;)

;;(deftemplate result (slot rqid) (slot optid) (slot score (default 0)) )

;;(deftemplate counter (slot rqid) (slot cnt (default 0)) (slot total (default 0)))

;;(deftemplate step (slot fstep (default 1)) (slot tstep (default 1)) (slot cstep (default 1)))

;;(deffacts transport-options
;;	(options (rqid "001") (optid "OPT1") (fare 17) (time 120) (changes 1) (evaluated FALSE))
;;)

;;(deffacts transport-options
;;	(options (rqid "001") (optid "OPT2") (fare 18) (time 114) (changes 1) (evaluated FALSE))
;;)

;;(deffacts transport-options
;;	(options (rqid "001") (optid "OPT3") (fare 10) (time 104) (changes 1) (evaluated FALSE))
;;)

;;(deffacts transport-options
;;	(options (rqid "001") (optid "OPT4") (fare 8) (time 104) (changes 2) (evaluated FALSE))
;;)

;;(deffacts transport-options
;;	(options (rqid "001") (optid "OPT5") (fare 10) (time 100) (changes 3) (evaluated FALSE))
;;)

;;(deffacts preferences
;;	(ordering (rqid "001") (farew (/ 1 3)) (timew (/ 1 9)) (changesw (/ 1 27)))
;;)

;;(deffacts steps
;;	(step (fstep 10) (tstep  20) (cstep 1))
;;)

;;(deffacts option-counter
;;  (counter (rqid "001") (cnt 0) (total 0))
;;)


;;(deffacts outcome
;;  (result (rqid "001") (optid "OPT1") (score 0))
;;)

;;(deftemplate step (slot value (default 1)) )
;;(deftemplate fstep extends step)
;;(deftemplate tstep extends step)
;;(deftemplate cstep extends step)

;;(deffacts steps
;;	(fstep (value 10)) (tstep (value 20)) (cstep (value 1)
;;)

(defrule Prioritize-options
  (declare (salience +100))
  ?opt <- (options (rqid ?requestid) (optid ?optionid) (fare ?tfare) (time ?ttime) (changes ?tchanges) (evaluated FALSE))
  (ordering (rqid ?requestid) (farew ?fweight) (timew ?tweight) (changesw ?cweight))
  (step (fstep ?fstep) (tstep ?tstep) (cstep ?cstep))
  ?counter <- (counter (rqid ?requestid) (cnt ?cnt) (total ?totalprocessed))
;;  ?result <- (result (rqid ?requestid) (optid ?optionid) (score ?score))
  =>
  (printout t "An option is being evaluated: rqID: " ?requestid " optID: " ?optionid " fare: " ?tfare " time: " ?ttime " changes: " ?tchanges crlf)

  (assert (result (rqid ?requestid) (optid ?optionid) (score (+ (+ (* (/ ?tfare ?fstep) ?fweight) (* (/ ?ttime ?tstep) ?tweight)) (* (/ ?tchanges ?cstep) ?cweight))) ))

  (modify ?opt (evaluated TRUE)) 
  (modify ?counter (cnt (+ ?cnt 1)) (total (+ ?totalprocessed 1)))
  (printout t "Option: " ?optionid " Score: " (+ (+ (* (/ ?tfare ?fstep) ?fweight) (* (/ ?ttime ?tstep) ?tweight)) (* (/ ?tchanges ?cstep) ?cweight)) crlf)
;;(bind ?more (run-query* options ?requestid))
;;(while (?more next)
)

(defrule Options-have-been-evaluated
  (declare (salience -1))
  ?opt <- (options (rqid ?requestid) (optid ?optionid) (fare ?tfare) (time ?ttime) (changes ?tchanges) (evaluated TRUE))
  ?result <- (result (rqid ?requestid) (optid ?optionid) (score ?score))
  ?counter <- (counter (rqid ?requestid) (cnt ?cnt))
  =>
  (printout t "An option has been processed, pls send the score" crlf)
  (SendScore ?requestid ?optionid ?score)
  (modify ?counter (cnt (- ?cnt 1)))
  (retract ?opt)
  (retract ?result)
  
)


(defrule send-signal-that-all-options-have-been-completely-treated-ready-for-display
  (declare (salience -100))
  ?counter <- (counter (rqid ?requestid) (cnt ?cnt&: (<= ?cnt 0)) (total ?totalprocessed))
;;  (test (eq ?cnt 0))
  =>
  (printout t "All processing has been completed and sent. Total processed: " ?totalprocessed  crlf)
  (SendEndSignal ?totalprocessed)
 
)  

;;(assert (responded ?from ?rqid))


;;(defrule Remove-treated-requests
;;  (declare (salience -1))
;;  ?opt <- (options (rqid ?requestid) (optid ?optionid) (fare ?tfare) (time ?ttime) ;;(changes ;;?tchanges) (evaluated TRUE))
;;  ?order <- (ordering (rqid ?requestid) (farew ?fweight) (timew ?tweight) (changesw ?;;cweight))
;;  ?result <- (result (rqid ?requestid) (optid ?optionid) (score ?score))
;;  =>
;;  (printout t "All options have been evaluated" crlf)
;;  (pass-results-back-to-java-program)
;;)




;;deffunction square (?n) (return (* ?n ?n)))


;;(deffacts generic_transport-options
;;	(options (rqid requestid) (optid optionid) (fare tfare) (time 	ttime) (changes ;;tchanges))
;;)


;;(reset)
;;(run)