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

import com.pauware.pauware_engine.Exceptions.State_exception;

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
public interface Manageable_base {

    /**
     * This method returns the current state of a software component.
     * <p>
     * Its typical implementation is as follows:
     * <CODE>return _my_state_machine.async_current_state();</CODE> where the
     * type of the <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}. While
     * {@link Manageable_base#current_state()} computes the real-time state of
     * <CODE>_my_state_machine</CODE> with a risk of unreliable result (if
     * called when a run-to-completion cycle is in progress), this method is
     * more reliable in the sense that it returns the recorded state in the very
     * last run-to-completion cycle.
     *
     * @return
     */
    String async_current_state();

    /**
     * This method returns the current (real-time) state of a software
     * component.
     * <p>
     * Its typical implementation is as follows:
     * <CODE>return _my_state_machine.current_state();</CODE> where the type of
     * the <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}.
     *
     * @return
     * @see Manageable_base#async_current_state()
     */
    String current_state();

    /**
     * This method detects if a given state of a software component is active.
     * <p>
     * Its typical implementation is as follows:
     * <CODE>return _my_state_machine.in_state("A state");</CODE> where the type
     * of the <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}.
     *
     * @param name
     * @return
     */
    boolean in_state(String name);

    /**
     * This method returns the preferred name of a software component to be
     * managed by a naming service.
     * <p>
     * Its typical implementation is as follows:
     * <CODE>return _my_state_machine.name();</CODE> where the type of the
     * <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}.
     *
     * @return
     */
    String name();

    /**
     * This method returns the last result of a run-to-completion cycle plus the
     * current state of a software component.
     * <p>
     * Its typical implementation is as follows:
     * <CODE>return _my_state_machine.verbose();</CODE> where the type of the
     * <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}.
     *
     * @return
     */
    String verbose();
}
