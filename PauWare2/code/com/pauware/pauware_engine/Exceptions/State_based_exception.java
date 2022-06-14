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

import com.pauware.pauware_engine.Core.AbstractState;

/**
 * This class is concerned with abnormal state configuration/structuring
 * exceptions. It is typically thrown when states are linked together with
 * <CODE>null</CODE> values. It is also raised when states are not properly
 * linked together with respect to the <I>nesting</I>, <I>xor</I> and <I>and</I>
 * operators.
 * <p>
 * Compatibility: <I>Java 9</I>. 
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public class State_based_exception extends State_exception {

    private static final long serialVersionUID = 2L;
    /**
     * This field refers to the first state involved in the problem creating the
     * exception.
     */
    AbstractState _first;
    /**
     * This field refers to the second state involved in the problem creating
     * the exception.
     */
    AbstractState _second;

    /**
     * @param message A text explaining the problem's cause.
     * @param first The first state involved in the problem.
     * @param second The second state involved in the problem.
     */
    public State_based_exception(String message, AbstractState first, AbstractState second) {
        super(message);
        _first = first;
        _second = second;
    }

    /**
     * @return A message explaining why two states cause a problem in the
     * configuration/layout of a state machine.
     */
    public String getMessage() {
        if (_first == null) {
            if (_second == null) {
                return super.getMessage() + ": 'first' and 'second' are both 'null'";
            } else {
                return super.getMessage() + ": 'first' is 'null' and 'second' is " + _second.name();
            }
        }
        if (_second == null) {
            return super.getMessage() + ": 'first' is " + _first.name() + " and 'second' is 'null'";
        }
        return super.getMessage() + ": 'first' is " + _first.name() + " and 'second' is " + _second.name();
    }
}
