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
 * This abstract class represents the general notion of <b>Guard</b> in UML.
 * Instances of this class are executed at event processing time (notation is:
 * <I>event[guard]/action(s)</I>). It is instantiated within the
 * {@link com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
 * method. Instances of this class are executed by means of the
 * {@link com.pauware.pauware_engine.Core.AbstractGuard#execute()} method during
 * a run-to-completion cycle
 * ({@link com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)}
 * method).
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
abstract public class AbstractGuard {

    /**
     * The action to be evaluated. This field is <CODE>null</CODE> when no
     * action is associated with a guard. In such a case, the
     * {@link AbstractGuard#_value} field is computed at the beginning of a
     * run-to-completion cycle and frozen during this cycle.
     */
    protected AbstractAction _action = null;
    /**
     * The result of the guard's evaluation. <b>Caution</b>: guards raising
     * exceptions or errors during their evaluation are set to
     * <b><CODE>false</CODE></b>.
     */
    protected boolean _value = false;

    /**
     * This constructor is called by the
     * {@link AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
     * method.
     *
     * @param guard_object The object in charge of evaluating the guard; Most of
     * the time, this object is the software component that runs the state
     * machine.
     * @param guard_action The action to be executed; The execution amounts to
     * evaluating the guard.
     * @param guard_args The action's arguments.
     */
    protected AbstractGuard(Object guard_object, String guard_action, Object[] guard_args) {
        if (guard_object != null) {
            if (guard_action != null) {
                _action = action(guard_object, guard_action, guard_args);
            }
        } else {
            if (guard_action != null && guard_action.equals("true")) {
                _value = true;
            }
        }
    }

    /**
     * Implementation of this method occurs in
     * {@link com.pauware.pauware_engine.Core.Guard}.
     *
     * @param guard_object
     * @param guard_action
     * @param guard_args
     * @return
     * @see com.pauware.pauware_engine.Core.Action
     */
    abstract protected AbstractAction action(Object guard_object, String guard_action, Object[] guard_args);

    /**
     * This method is used when a guard object is put in a map data structure as
     * a key.
     *
     * @param guard
     */
    @Override
    public boolean equals(Object guard) {
        if (this == guard) {
            return true;
        }
        if (guard instanceof AbstractGuard) {
            return _action == null ? true : _action.equals(((AbstractGuard) guard)._action) && _value == ((AbstractGuard) guard)._value;
        }
        return false;
    }

    /**
     * This method is used when a guard object is put in a map data structure as
     * a key.
     */
    @Override
    public int hashCode() {
        return (_action == null ? 0 : _action.hashCode()) ^ (_value ? 1 : 0);
    }

    /**
     * Implementation of this method depends upon the chosen platform,
     * <i>i.e.</i>, adaptation for platforms different from Java 9 have to
     * provide specific code.
     *
     * @return success, failure or <code>false</code> when executed guard does
     * not return a Boolean value.
     * @throws State_exception An encapsulation of any Java problem resulting
     * from evaluating the guard.
     */
    protected boolean execute() throws State_exception {
        if (_action == null) {
            return _value;
        }
        _action.execute();
        return _action._result instanceof Boolean ? ((Boolean) _action._result).booleanValue() : false;
    }

    /**
     * This method returns the detailed result of the guard's execution. It
     * calls the {@link AbstractAction#verbose()} method if an action is
     * associated with the guard. Otherwise, it returns either
     * <CODE>false</CODE> or <CODE>true</CODE>.
     *
     * @return The detailed result of the guard's execution as a string.
     */
    protected String verbose() {
        if (_action == null) {
            if (_value == true) {
                return "true";
            }
            if (_value == false) {
                return "false";
            }
        }
        return _action.verbose();
    }

    /**
     * This method returns the guard in the form of a UML-compliant string.
     *
     * @return The guard as UML-readable string.
     */
    public String to_UML() {
        return _action != null ? "[" + _action.to_UML() + "]" : "";
    }
}
