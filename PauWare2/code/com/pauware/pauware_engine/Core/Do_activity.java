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
 * This concrete class represents the general notion of <b>Activity</b> in UML
 * (<I>do/</I> notation).
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.3
 */
public class Do_activity extends SendSignalAction {

    /**
     * @param object
     * @param action
     * @param args
     * @see SendSignalAction#SendSignalAction(Object,String,Object[])
     */
    protected Do_activity(Object object, String action, Object[] args) {
        super(object, action, args);
    }

    /**
     * This method executes an activity (<I>do/</I> notation) in a standalone
     * thread of control.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see SendSignalAction#execute()
     */
    @Override
    public void execute() throws com.pauware.pauware_engine.Exceptions.State_exception {
        wait_for_completion();
        _thread = new Thread(this, _object.toString() + '.' + _action);
        try {
            _thread.start();
        } catch (IllegalThreadStateException itse) {
            _result = _thread.getName() + " failed: " + itse.toString();
            throw new com.pauware.pauware_engine.Exceptions.State_exception(_result.toString());
        }
    }

    /**
     * This method indefinitely waits for an activity to complete. Since UML
     * activities (<I>do/</I> notation) last, this method waits for the
     * completion of the immediate prior execution.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    @Override
    protected void wait_for_completion() throws com.pauware.pauware_engine.Exceptions.State_exception {
        if (_thread != null) {
            try {
                _thread.join();
            } catch (InterruptedException ie) {
                _result = _thread.getName() + " failed: " + ie.toString();
                throw new com.pauware.pauware_engine.Exceptions.State_exception(_result.toString());
            }
        }
    }
}
