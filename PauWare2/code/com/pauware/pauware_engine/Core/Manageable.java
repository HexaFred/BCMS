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
package com.pauware.pauware_engine.Core;

/**
 * This interface is a management utility, which can be implemented by a
 * software component so that it can be accessed by means of Java Management
 * eXtensions (JMX) in particular.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public interface Manageable extends Manageable_base {

    /**
     * This method forces a software component to move to a given state, thus
     * forcing its orthogonal states and superstates to be also active.
     * <p>
     * Its typical implementation is as follows:
     * <CODE>_my_state_machine.to_state("A state");</CODE> where the type of the
     * <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}. <b>Caution</b>: this method triggers the
     * <I>entry</I> and <I>exit</I> actions associated with states.
     *
     * @param name The name of the state to which the state machine goes to.
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see AbstractState#set_entryAction(Object,String,Object[],byte)
     * @see AbstractState#set_exitAction(Object,String,Object[],byte)
     * @see AbstractState#reset_entryAction(Object,String,Object[],byte)
     * @see AbstractState#reset_exitAction(Object,String,Object[],byte)
     */
    void to_state(String name) throws com.pauware.pauware_engine.Exceptions.State_exception;
}
