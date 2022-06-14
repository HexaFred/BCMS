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
 * This class is concerned with abnormal transition configuration/structuring
 * exceptions. It is for instance thrown when declared transitions are
 * ill-formed, <I>e.g.</I>, a transition from a state to its direct superstate.
 * It is also for instance thrown when a transition conflicts with another one.
 * <p>
 * Compatibility: <I>Java 9</I>. 
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 * @see
 * com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)
 */
public class Transition_based_exception extends State_exception {

    private static final long serialVersionUID = 2L;
    /**
     * This field refers to the source state of the transition causing the
     * problem.
     */
    AbstractState _from;
    /**
     * This field refers to the end state of the transition causing the problem.
     */
    AbstractState _to;

    /**
     * @param message A text explaining the problem's cause.
     * @param from The source state of the transition causing the problem.
     * @param to The end state of the transition causing the problem.
     */
    public Transition_based_exception(String message, AbstractState from, AbstractState to) {
        super(message);
        _from = from;
        _to = to;
    }

    /**
     * @return A message explaining why a transition is ill-formed or is a
     * source of conflict.
     */
    @Override
    public String getMessage() {
        if (_from == null) {
            if (_to == null) {
                return super.getMessage() + ": 'from' and 'to' are both 'null'";
            }
            return super.getMessage() + ": 'from' is 'null' - 'to' is " + _to.name();
        }
        if (_to == null) {
            return super.getMessage() + ": 'from' is " + _from.name() + " - 'to' is 'null'";
        }
        return super.getMessage() + ": 'from' is " + _from.name() + " - 'to' is " + _to.name();
    }
}
