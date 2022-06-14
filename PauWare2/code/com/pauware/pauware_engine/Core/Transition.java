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
 * This class represents the general notion of <b>Transition</b> in UML.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public class Transition {

    /**
     * This state at the source of the transition.
     */
    protected final AbstractState _from;
    /**
     * This state at the end of the transition.
     */
    protected final AbstractState _to;

    /**
     * This constructor creates a transition instance requiring that
     * <CODE>from</CODE> is not equal to <CODE>null</CODE> <b>and</b>
     * <CODE>to</CODE> is not equal to <CODE>null</CODE>. This pre-condition is
     * checked within the
     * {@link AbstractStateMachine#fires(String, AbstractState, AbstractState, Object, String, Object[], Object, String, Object[], byte)}
     * method.
     *
     * @param from
     * @param to
     */
    protected Transition(AbstractState from, AbstractState to) {
        _from = from;
        _to = to;
    }

    /**
     * This method is used when a transition object is put in a map data
     * structure as a key.
     *
     * @param transition
     */
    @Override
    public boolean equals(Object transition) {
        if (this == transition) {
            return true;
        }
        if (transition instanceof Transition) {
            return _from.equals(((Transition) transition)._from) && _to.equals(((Transition) transition)._to);
        }
        return false;
    }

    /**
     * This method is used when a transition object is put in a map data
     * structure as a key.
     */
    @Override
    public int hashCode() {
        return _from.hashCode() * 31 + _to.hashCode();
    }

    /**
     * This method is an accessor for the {@link Transition#_from} field.
     *
     * @return
     */
    public AbstractState from() {
        return _from;
    }

    /**
     * This method is an accessor for the {@link Transition#_to} field.
     *
     * @return
     */
    public AbstractState to() {
        return _to;
    }
}
