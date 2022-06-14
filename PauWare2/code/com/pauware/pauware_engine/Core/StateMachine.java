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
 * This concrete class represents the general notion of <b>State Machine</b> in
 * UML.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public class StateMachine extends AbstractStateMachine implements java.io.Serializable {

    private static final long serialVersionUID = 2L;

    /* Vertical composition issues: constructor used by createFather()
     private StateMachine(){
     super();
     }
     */
    /**
     * @param s
     * @param name
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see AbstractStateMachine#AbstractStateMachine(AbstractState,String)
     */
    public StateMachine(AbstractState s, String name) throws com.pauware.pauware_engine.Exceptions.State_exception {
        super(s, name);
    }

    /**
     * @param s
     * @param name
     * @param show_on_system_err
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see
     * AbstractStateMachine#AbstractStateMachine(AbstractState,String,boolean)
     */
    public StateMachine(AbstractState s, String name, boolean show_on_system_err) throws com.pauware.pauware_engine.Exceptions.State_exception {
        super(s, name, show_on_system_err);
    }

    /**
     * @param s
     * @param name
     * @param show_on_system_out
     * @param listener
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see
     * AbstractStateMachine#AbstractStateMachine(AbstractState,String,boolean,AbstractStateMachine_listener)
     */
    public StateMachine(AbstractState s, String name, boolean show_on_system_out, AbstractStateMachine_listener listener) throws com.pauware.pauware_engine.Exceptions.State_exception {
        super(s, name, show_on_system_out, listener);
    }

    /**
     * @param s
     * @param name
     * @param show_on_system_out
     * @param listener1
     * @param listener2
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public StateMachine(AbstractState s, String name, boolean show_on_system_out, AbstractStateMachine_listener listener1, AbstractStateMachine_listener listener2) throws com.pauware.pauware_engine.Exceptions.State_exception {
        super(s, name, show_on_system_out, listener1, listener2);
    }

    /**
     * This method raises an instance of <CODE>RuntimeException</CODE>. This is
     * because <CODE>AbstractStateMachine</CODE> instances are "root" states and
     * thus may not be composed in larger state machines. However, this method
     * is intended to be replaced as soon as the notion of "vertical
     * composition" will be implemented within <I>PauWare</I>.
     *
     * @return
     */
    @Override
    protected AbstractState createFather() {
        throw new RuntimeException("Attempting to creating a 'father' state for a root state");
        // Vertical composition issues:
        // return new StateMachine();
        // memory burden, too many monitors in the state hierarchy
    }

    /**
     * This method creates and returns an action instance that is compatible
     * with the chosen platform, <i>i.e.</i>, adaptation for platforms different
     * from Java 9 have to provide specific code.
     *
     * @param object
     * @param action
     * @param args
     * @param reentrance_mode
     * @return This is an instance of {@link Action} if the
     * <CODE>reentrance_mode</CODE> parameter is equal to
     * <CODE>AbstractState.No_reentrance</CODE>; Otherwise, it is an instance of
     * {@link SendSignalAction}.
     */
    @Override
    protected AbstractAction action(Object object, String action, Object[] args, byte reentrance_mode) {
        switch (reentrance_mode) {
            case No_reentrance:
                return new Action(object, action, args);
            case Reentrance:
                return new SendSignalAction(object, action, args);
            default:
                return new SendSignalAction(object, action, args);
        }
    }

    /**
     * This method creates and returns an action instance that is compatible
     * with the chosen platform, <i>i.e.</i>, adaptation for platforms different
     * from Java 9 have to provide specific code.
     *
     * @param object
     * @param action
     * @param args
     * @return This is an instance of {@link Do_activity}.
     */
    @Override
    protected AbstractAction activity(Object object, String action, Object[] args) {
        return new Do_activity(object, action, args);
    }

    /**
     * * This method creates and returns a guard instance that is compatible
     * with the chosen platform, <i>i.e.</i>, adaptation for platforms different
     * from Java 9 have to provide specific code.
     *
     * @param guard_object
     * @param guard_action
     * @param guard_args
     * @return this is an instance of {@link Guard}.
     */
    @Override
    protected AbstractGuard guard(Object guard_object, String guard_action, Object[] guard_args) {
        return new Guard(guard_object, guard_action, guard_args);
    }
}
