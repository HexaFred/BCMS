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
 * This concrete class represents the general notion of <b>Guard</b> in UML.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public class Guard extends AbstractGuard {

    /**
     * @param guard_object
     * @param guard_action
     * @param guard_args
     * @see AbstractGuard#AbstractGuard(Object,String,Object[])
     */
    protected Guard(Object guard_object, String guard_action, Object[] guard_args) {
        super(guard_object, guard_action, guard_args);
    }

    /**
     * This method creates and returns an action instance that is compatible
     * with the chosen platform,
     * <i>i.e.</i>, adaptation for platforms different from Java 9 have to
     * provide specific code.
     *
     * @param guard_object
     * @param guard_action
     * @param guard_args
     * @return This is an instance of {@link Action}. Note that guards cannot
     * send signals, and thus should only evaluate local data. As a result, an
     * instance of the {@link SendSignalAction} class cannot be returned.
     */
    @Override
    protected AbstractAction action(Object guard_object, String guard_action, Object[] guard_args) {
        return new Action(guard_object, guard_action, guard_args);
    }
}
