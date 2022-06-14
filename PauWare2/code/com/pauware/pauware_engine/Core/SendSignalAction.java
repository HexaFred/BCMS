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
 * This concrete class represents the general notion of <b>Action</b> in UML,
 * which consists in sending a signal. Instances of this class result from using
 * the {@link com.pauware.pauware_engine.Core.AbstractState#Reentrance} value as
 * the last parameter of the
 * {@link com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
 * method.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public class SendSignalAction extends Action implements Runnable {

    /**
     * This field refers to the action (if any) being executed; it corresponds
     * to sending a signal.
     */
    protected Thread _thread = null;

    /**
     * @param object
     * @param action
     * @param args
     * @see Action#Action(Object,String,Object[])
     */
    protected SendSignalAction(Object object, String action, Object[] args) {
        super(object, action, args);
    }

    /**
     * This method executes an action in a standalone thread of control. Such an
     * action is the self-sending of an event.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see Action#execute()
     */
    @Override
    public void execute() throws com.pauware.pauware_engine.Exceptions.State_exception {
        _thread = new Thread(this, _object.toString() + '.' + _action);
        /**
         * Modified on Nov. 2013
         */
        /**
         * The line of code below has been added because models are often
         * ill-formed. Indeed, modelers wrongly believe that a 's' event sent
         * internally by 'a', is processed BEFORE 'b', which is the event
         * following 'a' in a given state machine sequence. Java tends to
         * process 's' too late. So, one may improve the situation, given 's' a
         * higher processing priority, but the best amounts to building more
         * robust models!
         */
        _thread.setPriority(Thread.MAX_PRIORITY);
        /**
         * End of modification
         */
        try {
            _thread.start();
        } catch (IllegalThreadStateException itse) {
            _result = _thread.getName() + " failed: " + itse.toString();
            throw new com.pauware.pauware_engine.Exceptions.State_exception(_result.toString());
        }
    }

    /**
     * This method calls the {@link Action#execute()} method in order to
     * synchronize (defer) the action execution with respect to any
     * run-to-completion cycle that is in progress.
     */
    @Override
    public void run() {
        try {
            super.execute();
        } catch (com.pauware.pauware_engine.Exceptions.State_exception se) {
            /**
             * 'se' is already assigned to '_result'
             */
            se.printStackTrace();
        }
    }

    /**
     * This method returns the action in the form of a UML-compliant string.
     */
    @Override
    public String to_UML() {
        String result = super.to_UML();
        return result != null ? SendSignalAction_symbol + result : null;
    }
}
