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
 * This abstract class represents the general notion of <b>Action</b> in UML.
 * Instances of this class are executed when entering states (<I>entry/</I>
 * notation), exiting states (<I>exit/</I> notation) and at event processing
 * time (<I>event[guard]/action(s)</I> notation). Use of this class occurs
 * through the following subclasses:
 * {@link com.pauware.pauware_engine.Core.Action}, {@link com.pauware.pauware_engine.Core.SendSignalAction}.
 * It is instantiated within the
 * {@link com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
 * method. Instances of this class are executed by means of the
 * {@link com.pauware.pauware_engine.Core.AbstractAction#execute()} method
 * during a run-to-completion cycle
 * ({@link com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)}
 * method).
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
abstract public class AbstractAction {

    /**
     * This class variable is the symbol of an action, which sends a signal (an
     * event).
     */
    public static final String SendSignalAction_symbol = "^";

    /**
     * The default value is the <CODE>"no result" String</CODE> instance if
     * there is no execution.
     */
    public static final String Pseudo_result = "no result";
    /**
     * This field represents the object in charge of executing the action.
     */
    protected Object _object;
    /**
     * This field represents the action itself.
     */
    protected String _action;
    /**
     * This field represents the action's arguments.
     */
    protected Object[] _args;
    /**
     * This field represents the action's result, if already executed.
     *
     * @see AbstractAction#Pseudo_result
     */
    protected Object _result;

    /**
     * This constructor is called by the
     * {@link AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
     * method.
     *
     * @param object The object executing the action; Java arrays are supported.
     * @param action The executed action (<CODE>null</CODE> means no effect).
     * @param args The action's arguments (<CODE>null</CODE> means no
     * arguments).
     */
    protected AbstractAction(Object object, String action, Object[] args) {
        _object = object;
        _action = action;
        set_args(args);
        _result = Pseudo_result;
    }

    /**
     * This method is used when an action object is put in a map data structure
     * as a key.
     *
     * @param action
     */
    @Override
    public boolean equals(Object action) {
        if (this == action) {
            return true;
        }
        if (action instanceof AbstractAction) {
            return (_object == null ? ((AbstractAction) action)._object == null : _object.equals(((AbstractAction) action)._object)) && (_action == null ? ((AbstractAction) action)._action == null : _action.equals(((AbstractAction) action)._action)) && (_args == null ? ((AbstractAction) action)._args == null : compare_args(((AbstractAction) action)._args));
        }
        return false;
    }

    /**
     * This method is used when an action object is put in a map data structure
     * as a key.
     */
    @Override
    public int hashCode() {
        return (_object == null ? 0 : _object.hashCode()) ^ (_action == null ? 0 : _action.hashCode()) ^ (_args == null ? 0 : hashCode_args());
    }

    /**
     * Implementation of this method depends upon the chosen platform,
     * <i>i.e.</i>, adaptation for platforms different from Java 9 have to
     * provide specific code.
     *
     * @throws State_exception An encapsulation of any Java problem that results
     * from executing the action
     */
    abstract protected void execute() throws State_exception;

    /**
     * This method allows the possibility of waiting for actions to complete.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    abstract protected void wait_for_completion() throws State_exception;

    /**
     * This method is called by the {@link AbstractAction#equals(Object)}
     * method.
     *
     * @param args
     * @return
     */
    protected boolean compare_args(Object[] args) {
        if (args == null) {
            return _args == null ? true : false;
        }
        if (args.length != _args.length) {
            return false;
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null && _args[i] != null && !(_args[i].getClass().isInstance(args[i]))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called by the {@link AbstractAction#hashCode()} method.
     * <b>Caution</b>: actions are known to be similar when the type and the
     * number of their arguments are the same. The identities of their arguments
     * are NOT used at comparison time. In this perspective, this method
     * guarantees that <CODE>a1.equals(a2)</CODE> implies
     * <CODE>a1.hashCode() == a2.hashCode()</CODE>.
     *
     * @return
     */
    public int hashCode_args() {
        // pre-condition: _args != null
        int hashCode = 0;
        for (int i = 0; i < _args.length; i++) {
            hashCode ^= (_args[i] == null ? 0 : _args[i].getClass().hashCode());
        }
        return hashCode;
    }

    /**
     * This method is called by the
     * {@link AbstractAction#AbstractAction(Object,String,Object[])}
     * constructor.
     *
     * @param args
     */
    protected void set_args(Object[] args) {
        if (args == null) {
            _args = null;
        } else {
            _args = new Object[args.length];
            for (int i = 0; i < _args.length; i++) {
                _args[i] = args[i];
            }
        }
    }

    /**
     * This method is called by the {@link AbstractAction#verbose()} method.
     *
     * @return
     */
    protected String printable_action() {
        if (_action == null) {
            return "null";
        }
        return _action;
    }

    /**
     * This method is called by the {@link AbstractAction#verbose()} method.
     *
     * @return
     */
    protected String printable_object() {
        if (_object == null) {
            return "null";
        }
        return _object.getClass().getSimpleName();
    }

    /**
     * This method is called by the {@link AbstractAction#verbose()} method.
     *
     * @return
     */
    protected String printable_result() {
        if (_result == null) {
            return "null";
        }
        if (_result.toString().indexOf('@') == -1) {
            return _result.toString();
        } else {
            return _result.getClass().getName().substring(_result.getClass().getName().lastIndexOf('.') + 1) + _result.toString().substring(_result.toString().indexOf('@'));
        }
        /**
         * Java SE
         */
//        return _result.toString().replace(_result.getClass().getName(), _result.getClass().getSimpleName());
        /**
         * End of Java SE
         */
    }

    /**
     * This method returns the detailed result of the action's execution, or the
     * content of the {@link AbstractAction#Pseudo_result} class variable in
     * case of the {@link AbstractAction#execute()} method has not yet been run.
     *
     * @return
     */
    protected String verbose() {
        return to_UML() + AbstractState.Textual_view_subject_separator + "result: " + printable_result();
    }

    /**
     * This method returns the action in the form of a UML-compliant string.
     *
     * @return
     */
    public String to_UML() {
        String label = "";
        label += printable_object() + '.' + printable_action();
        if (_args != null) {
            label += '(';
            for (int i = 0; i < _args.length; i++) {
                if (_args[i] == null) {
                    label += Object.class.getName().substring(Object.class.getName().lastIndexOf('.') + 1) + ',';
                } else {
                    label += _args[i].getClass().getName().substring(_args[i].getClass().getName().lastIndexOf('.') + 1) + ',';
                    /**
                     * Java SE
                     */
//                label += _args[i].getClass().getSimpleName() + ',';
                    /**
                     * End of Java SE
                     */
                }
            }
            label = label.substring(0, label.lastIndexOf(',')) + ')';
        }
        return label;
    }
}
