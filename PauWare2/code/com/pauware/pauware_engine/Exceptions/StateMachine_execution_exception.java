/**
 * PauWare engine software (www.PauWare.com) InterDeposit Digital Number
 * IDDN.FR.001.360023.000.S.P.2006.000.10000.
 *
 * MIT License
 *
 * Copyright (c) 2021 Franck.Barbier@FranckBarbier.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package com.pauware.pauware_engine.Exceptions;

/**
 * This class is concerned with abnormal situations or failures when the
 * <I>PauWare</I> engine is running. It acts as an encapsulation means for all
 * kinds of problems that may occur in Java. Any problem that may occur during a
 * run-to-completion cycle is identified in creating an instance of this
 * exception class.
 * <p>
 * Compatibility: <I>Java 9</I>. 
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 * @see java.lang.Throwable
 * @see
 * com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)
 */
public class StateMachine_execution_exception extends State_exception {

    private static final long serialVersionUID = 2L;
    /**
     * This field refers to the problem creating the exception.
     */
    Object _problem;

    /**
     * @param message A text explaining the problem's cause.
     * @param problem The source object of the problem.
     */
    public StateMachine_execution_exception(String message, Object problem) {
        super(message);
        _problem = problem;
    }

    /**
     * @return A message explaning why a transition is ill-formed.
     */
    public String getMessage() {
        if (_problem == null) {
            return super.getMessage();
        }
        if (_problem instanceof Throwable) {
            return ((Throwable) _problem).getMessage();
        }
        return super.getMessage() + ": " + _problem.toString();
    }
}
