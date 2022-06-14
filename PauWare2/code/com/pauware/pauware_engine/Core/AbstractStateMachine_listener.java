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
 * This interface is a management utility (including visualization issues),
 * which can be implemented by a state machine manager object. This object is
 * intended to get information about the discrete evolution of a state machine.
 * It provides basic services so that this manager object is able to be informed
 * of cyclic changes coming from the managed component (<I>i.e.</I>, that owning
 * the state machine).
 * <p>
 * Compatibility:
 * <I>PauWare Java EE</I> (Java SE/Java EE) and <I>PauWare Java ME</I> (Java
 * SE/Java ME).
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public interface AbstractStateMachine_listener {

    /**
     * This method pushes (communicates) the initial structure of a state
     * machine. Such a structure includes all of the states, their relationships
     * and the cached transitions, <I>i.e.</I>, those declared before calling
     * the {@link AbstractStateMachine#start()} method.
     *
     * @param state_machine the state machine itself.
     * @throws java.lang.Exception
     */
    void post_construct(final AbstractStateMachine state_machine) throws Exception;

    /**
     * This method is called when entering an entire state machine; it is called
     * within the {@link AbstractStateMachine#start()} method.
     *
     * @param verbose A string stating what happened.
     * @throws java.lang.Exception
     */
    void start(final String verbose) throws Exception;

    /**
     * This method pushes (communicates) the detailed result of an execution
     * (trace) in a verbose mode just after a state machine execution; it also
     * communicates the effective triggered transitions. This occurs after a
     * run-to-completion cycle.
     *
     * @param verbose A string stating what happened.
     * @param execution The set of {@link Transition} objects (keys) and their
     * associated actions (values are Java primitive arrays of
     * {@link AbstractAction}.
     * @throws java.lang.Exception
     */
    void run_to_completion(final String verbose, final java.util.HashMap<Transition, Object[]> execution) throws Exception;

    /**
     * This method is called when exiting an entire state machine; it is called
     * within the {@link AbstractStateMachine#stop()} method.
     *
     * @param verbose A string stating what happened.
     * @throws java.lang.Exception
     */
    void stop(final String verbose) throws Exception;

    /**
     * This method is called after a state machine has been stopped.
     *
     * @throws java.lang.Exception
     */
    void pre_destroy() throws Exception;
}
