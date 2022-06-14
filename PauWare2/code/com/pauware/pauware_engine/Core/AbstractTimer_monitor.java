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

import java.util.Timer;
import java.util.TimerTask;

import com.pauware.pauware_engine.Exceptions.State_exception;

/**
 * This abstract class represents the notion of <I>timer services</I>.
 * <p>
 * A
 * software component may inherit from this abstract class in order to process
 * (react to) <I>timer events</I> (see:
 * {@link AbstractTimer_monitor#time_out(long,AbstractState)}). In
 * <I>PauWare</I>, timers are cyclic; They stop on demand only (see:
 * {@link AbstractTimer_monitor#to_be_killed()} or
 * {@link AbstractTimer_monitor#to_be_killed(AbstractState)}).
 * <p>
 * Compatibility: <I>Java 9</I>. 
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
abstract public class AbstractTimer_monitor implements Timed_component {

    /**
     * This field records the timers associated with <b>non</b>
     * <CODE>null</CODE> contexts. Contexts are keys while timers are values of
     * this map data structure instance.
     */
    private java.util.Map<AbstractState, Timer> _contexts = new java.util.HashMap<>();
    /**
     * This field records the last problem (if any) resulting from the use of
     * timer services.
     *
     * @see AbstractTimer_monitor#time_out_error(State_exception)
     */
    private State_exception _se = null;
    /**
     * This field represents the timer associated with the <CODE>null</CODE>
     * context.
     */
    private Timer _timer = null;

    /**
     * This method amounts to calling <CODE>to_be_killed(null);</CODE>.
     * <p>
     * Killing the unique timer (if any) associated with the <CODE>null</CODE>
     * context, does not lead to killing the other timers.
     */
    public void to_be_killed() {
        if (_timer == null) {
            return;
        }
        _timer.cancel();
        _timer = null;
    }

    /**
     * This method kills the unique timer (if any) associated with the
     * <CODE>context</CODE> parameter.
     * <p>
     * Killing the unique timer (if any) associated with the
     * <CODE>context</CODE> parameter, does not lead to killing the other
     * timers, including that associated with the <CODE>null</CODE> context.
     *
     * @param context
     */
    public void to_be_killed(final AbstractState context) {
        Timer timer = _contexts.get(context);
        if (timer == null) {
            return;
        }
        timer.cancel();
        _contexts.remove(context);
    }

    /**
     * This method amounts to calling <CODE>to_be_reset(null,delay);</CODE>.
     * <p>
     * Only one timer may exist at a time which has the <CODE>null</CODE>
     * context.
     *
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void to_be_reset(final long delay) throws State_exception {
        to_be_killed();
        to_be_set(delay);
    }

    /**
     * This method is the same as
     * {@link AbstractTimer_monitor#to_be_reset(long)}; It guarantees
     * compatibility with Java SE 1.4.x.
     *
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void to_be_reset(final Long delay) throws State_exception {
        to_be_reset(delay.longValue());
    }

    /**
     * This method amounts to killing the unique timer (if any) associated with
     * the <CODE>context</CODE> parameter and next setting up this timer with
     * the new <CODE>delay</CODE> parameter.
     * <p>
     * Only one timer may exist at a time having the <CODE>context</CODE>
     * parameter. If the context does not exist (the <CODE>null</CODE> context
     * may be used as parameter), no exception is raised.
     *
     * @param context
     * @param delay
     * @see AbstractTimer_monitor#to_be_killed(AbstractState)
     * @see AbstractTimer_monitor#to_be_set(AbstractState,long)
     * @throws State_exception An encapsulation of a possible exception raised
     * by the {@link java.util.Timer} or {@link java.util.TimerTask} classes
     */
    public void to_be_reset(final AbstractState context, final long delay) throws State_exception {
        to_be_killed(context);
        to_be_set(context, delay);
    }

    /**
     * This method is the same as
     * {@link AbstractTimer_monitor#to_be_reset(AbstractState,long)}; It
     * guarantees compatibility with Java SE 1.4.x.
     *
     * @param context
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void to_be_reset(final AbstractState context, final Long delay) throws State_exception {
        to_be_reset(context, delay.longValue());
    }

    /**
     * This method amounts to calling <CODE>to_be_set(null,delay);</CODE>.
     *
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void to_be_set(final long delay) throws State_exception {
        _se = null;
        if (_timer != null) {
            return;
        }
        TimerTask timer_task = new TimerTask() {
            public void run() {
                try {
                    if (_se != null) {
                        to_be_killed();
                        time_out_error(_se);
                    } else {
                        time_out(delay, null);
                    }
                } catch (State_exception se) {
                    _se = se;
                }
            }
        };
        _timer = new Timer();
        try {
            _timer.schedule(timer_task, delay, delay);
        } catch (IllegalArgumentException iae) {
            throw new State_exception(iae.toString() + ": " + delay);
        } catch (IllegalStateException ise) {
            throw new State_exception(ise.toString() + ": " + delay);
        }
    }

    /**
     * This method is the same as {@link AbstractTimer_monitor#to_be_set(long)};
     * It guarantees compatibility with Java SE 1.4.x.
     *
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void to_be_set(final Long delay) throws State_exception {
        to_be_set(delay.longValue());
    }

    /**
     * This method is called by a software component when <I>timer services</I>
     * are required.
     * <p>
     * The <CODE>context</CODE> parameter may be <CODE>null</CODE>. In any case,
     * calling this method while a timer is already assigned to the
     * <CODE>context</CODE> parameter has no effect; So, use
     * {@link AbstractTimer_monitor#to_be_reset(AbstractState,long)} instead.
     * Otherwise (in case of success), a software component will therefore
     * receive, cyclically, a <I>time-out</I> event each time, the
     * <CODE>delay</CODE> parameter is elapsed.
     *
     * @param context
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see
     * com.pauware.pauware_engine.Core.AbstractTimer_monitor#time_out(long,AbstractState)
     */
    public void to_be_set(final AbstractState context, final long delay) throws State_exception {
        _se = null;
        if (_contexts.containsKey(context)) {
            return;
        }
        TimerTask timer_task = new TimerTask() {
            public void run() {
                try {
                    if (_se != null) {
                        to_be_killed(context);
                        time_out_error(_se);
                    } else {
                        time_out(delay, context);
                    }
                } catch (State_exception se) {
                    _se = se;
                }
            }
        };
        Timer timer = new Timer();
        _contexts.put(context, timer);
        try {
            timer.schedule(timer_task, delay, delay);
        } catch (IllegalArgumentException iae) {
            throw new State_exception(iae.toString() + ": " + context._name + "-" + delay);
        } catch (IllegalStateException ise) {
            throw new State_exception(ise.toString() + ": " + context._name + "-" + delay);
        }
    }

    /**
     * This method is the same as
     * {@link AbstractTimer_monitor#to_be_set(AbstractState,long)}; It
     * guarantees compatibility with Java SE 1.4.x.
     *
     * @param context
     * @param delay
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void to_be_set(final AbstractState context, final Long delay) throws State_exception {
        to_be_set(context, delay.longValue());
    }

    /**
     * @throws State_exception An encapsulation of a possible exception raised
     * by the
     * {@link com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
     * or
     * {@link com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)}
     * methods
     */
    abstract public void time_out(long delay, AbstractState context) throws State_exception;

    /**
     * @throws State_exception Having this exception type in the signature
     * allows the use of methods themselves raising such an exception type
     */
    abstract public void time_out_error(State_exception se) throws State_exception;
}
