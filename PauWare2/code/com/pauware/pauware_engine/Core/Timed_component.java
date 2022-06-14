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
 * This interface is a utility that can be implemented by a software component
 * which wants to have <I>timer services</I> at its disposal. However,
 * inheriting from {@link com.pauware.pauware_engine.Core.AbstractTimer_monitor}
 * is preferable because this class provides a default implementation.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public interface Timed_component {

    /**
     * This method is called when <CODE>delay</CODE> is elapsed.
     * <p>
     * A software component receiving a
     * <I>time-out</I> may check the delay and/or the context in which, it asks
     * for <I>timer services</I>. Normally, the content of this method starts
     * with several transitions (see the
     * {@link com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
     * method) and ends with a run-to-completion cycle, namely
     * <CODE>_my_state_machine.run_to_completion("time_out");</CODE> where the
     * type of the <CODE>_my_state_machine</CODE> field in the component is
     * {@link AbstractStateMachine}.
     *
     * @param delay
     * @param context
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    void time_out(long delay, AbstractState context) throws com.pauware.pauware_engine.Exceptions.State_exception;

    /**
     * This method is called when <I>timer services</I> raise problems.
     * <p>
     * A software component receiving a <I>time-out error</I> may know the
     * reason why the requested <I>timer services</I> fail (<CODE>se</CODE>
     * parameter). The content of this method may be left empty. The requested
     * <I>timer services</I> that are causing problems are indeed automatically
     * canceled.
     *
     * @param se
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    void time_out_error(com.pauware.pauware_engine.Exceptions.State_exception se) throws com.pauware.pauware_engine.Exceptions.State_exception;
}
