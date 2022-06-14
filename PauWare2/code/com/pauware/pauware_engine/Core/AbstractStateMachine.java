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

import com.pauware.pauware_engine.Exceptions.*;

/**
 * This abstract class represents the general notion of <I>State Machine</I> in
 * UML. Use of this class occurs through the following subclass:
 * {@link com.pauware.pauware_engine.Core.StateMachine}.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
abstract public class AbstractStateMachine extends AbstractState implements Manageable {

    /**
     * This class variable is a constant value that disactivates the calculation
     * of state invariants.
     *
     * @see
     * com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)
     */
    public static final boolean Don_t_compute_invariants = false;
    /**
     * This class variable is a constant value that activates the calculation of
     * state invariants.
     *
     * @see
     * com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)
     */
    public static final boolean Compute_invariants = !AbstractStateMachine.Don_t_compute_invariants;
    /**
     * This class variable is a constant value which represents the fact that
     * the verbose mode for event processing is not activated.
     *
     * @see
     * AbstractStateMachine#AbstractStateMachine(AbstractState,String,boolean)
     */
    public static final boolean Don_t_show_on_system_out = false;
    /**
     * This class variable is a constant value which represents the fact that
     * the verbose mode for event processing is activated.
     *
     * @see
     * AbstractStateMachine#AbstractStateMachine(AbstractState,String,boolean)
     */
    public static final boolean Show_on_system_out = !AbstractStateMachine.Don_t_show_on_system_out;
    /**
     * This field records the chosen verbose mode associated with the state
     * machine.
     *
     * @see AbstractStateMachine#Don_t_show_on_system_out
     * @see AbstractStateMachine#Show_on_system_out
     */
    protected boolean _show_on_system_out = Don_t_show_on_system_out;
    /**
     * This field represents all of the eligible transitions of a given
     * run-to-completion cycle. This <CODE>Map</CODE> instance is empty at the
     * beginning of a run-to-completion cycle.
     */
    protected java.util.Map<Transition, Object[]> _execution = new java.util.HashMap<>();
    /**
     * This field records, during a run-to-completion cycle, the transitions to
     * be eliminated. Eliminated transitions are the transitions overridden by
     * other similar ones bound to substates. They are also those inhibited by
     * allowed events.
     */
    protected java.util.Vector<Transition> _eliminated_transitions = new java.util.Vector<>();
    /**
     * This field represents all of the cached transitions of the state machine.
     */
    protected java.util.Map<Transition, java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>>> _transitions = new java.util.HashMap<>();
    /**
     * This field records listeners, if any, for the software component that
     * owns the state machine.
     */
    protected java.util.ArrayList<AbstractStateMachine_listener> _listeners = new java.util.ArrayList<>();

    /**
     * This method adds a listener to the state machine; this method MUST be
     * called AFTER the state machine has started. A typical use of this method
     * is when a viewer is added in order to graphically simulate a state
     * machine as follows:
     * <CODE>_Programmable_thermostat.add_listener(new com.pauware.PauWare2Web.PauWare2Web_client());</CODE>
     *
     * @param listener
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see AbstractStateMachine#start()
     * @see AbstractStateMachine#remove_listener(AbstractStateMachine_listener)
     */
    synchronized public void add_listener(AbstractStateMachine_listener listener) throws State_exception {
        try {
            if (!_listeners.contains(listener)) {
                _listeners.add(listener);
            }
            listener.post_construct(this);
            listener.start("*** Deferred START");
        } catch (Exception e) {
            throw new State_exception(e.getMessage());
        }
    }

    /**
     * This method removes a listener of the state machine; this method MUST be
     * called BEFORE the state machine has stopped.
     *
     * @param listener
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see AbstractStateMachine#stop()
     * @see AbstractStateMachine#add_listener(AbstractStateMachine_listener)
     */
    synchronized public void remove_listener(AbstractStateMachine_listener listener) throws State_exception {
        try {
            _listeners.remove(listener);
            listener.stop("*** Unanticipated STOP");
            listener.pre_destroy();
        } catch (Exception e) {
            throw new State_exception(e.getMessage());
        }
    }
    /**
     * This class variable is a constant value which represents the fact that
     * the state machine's current state is not yet defined.
     *
     * @see AbstractStateMachine#in_state(String)
     */
    protected final static String _Undefined = "Undefined";
    /**
     * This field records the state of a state machine computed within of a
     * run-to-completion step.
     *
     * @see Manageable_base#async_current_state()
     * @see Manageable_base#current_state()
     */
    protected String _current_state = _Undefined;
    /**
     * This field records the result of an event occurrence processing within a
     * run-to-completion step.
     *
     * @see AbstractStateMachine#Don_t_show_on_system_out
     * @see AbstractStateMachine#Show_on_system_out
     */
    protected StringBuffer _verbose = new StringBuffer();

    /**
     * This constructor creates a father for two composed
     * <CODE>AbstractStateMachine</CODE> instances.
     *
     * @since ver. 1.2
     *
     * @see AbstractState#createFather()
     */
    protected AbstractStateMachine() {
        super(null);
    }

    /**
     * This constructor amounts to calling
     * <CODE>AbstractStateMachine(s,name,AbstractStateMachine.Don_t_show_on_system_out)</CODE>.
     * It supports the composition of <CODE>AbstractStateMachine</CODE>
     * instances (a.k.a.<I>vertical composition</I>) since ver. 1.2.
     *
     * @param s
     * @param name
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public AbstractStateMachine(AbstractState s, String name) throws State_exception {
        super(name);
        if (s == null) {
            throw new State_exception("AbstractStateMachine initialization with 'null' value");
            // _node == this; // not compatible with 'commonSuperWith'
        }
        _left = s._left;
        _right = s._right;
        s._left._node = this;
        s._right._node = this;
        _xor = s._xor;
        /**
         * Completion transitions (i.e., those without any event label) are
         * associated with output states.
         *
         * @since ver. 1.3
         */
        set_completion(this);
        // Cyril:
        // if(s instanceof AbstractStateMachine) mergeVariables((AbstractStateMachine)s);
    }

    /**
     * Completion transitions (i.e., those without any event label) are
     * automatically triggered when reaching output states.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @since ver. 1.3
     */
    public void completion() throws State_exception {
        run_to_completion(Completion);
    }

    /**
     * This constructor first creates and initializes a state machine; It next
     * triggers all <I>entry</I> actions associated with all the explicit and
     * implicit default input states of the state machine. Running this
     * constructor therefore implies that all resources, used by the state
     * machine in <I>entry</I> actions, are available and in an appropriate
     * status. Use of this class occurs through the following subclass:
     * {@link com.pauware.pauware_engine.Core.StateMachine}. Example of a
     * user-defined <CODE>Stack</CODE> software component:
     * <p>
     * <CODE>
     * <br>AbstractStateMachine _Stack;
     * <br>...
     * <br>_Stack = new StateMachine(_Empty.xor(_Not_empty),"Stack");
     * </CODE>
     *
     * @param s A state object resulting from a XOR-based state composition or
     * an AND-based state composition
     * @param name A name assigned to the state machine. The name of the
     * software component type running this state machine is preferred for this
     * parameter
     * @param show_on_system_out If <CODE>true</CODE>, this parameter activates
     * the verbose mode of a run-to-completion cycle (for tests only)
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public AbstractStateMachine(AbstractState s, String name, boolean show_on_system_out) throws State_exception {
        this(s, name);
        _show_on_system_out = show_on_system_out;
    }

    /**
     * This constructor amounts to calling
     * <CODE>AbstractStateMachine(s,name,show_on_system_out)</CODE> plus the
     * definition of a state machine's listener.
     *
     * @param s
     * @param name
     * @param show_on_system_out
     * @param listener The state machine's listener is first initialized in
     * receiving the state machine itself (for display purposes for instance)
     * Next, it is informed of the state machine's status changes at the time
     * when each run-to-completion cycle is finished.
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see
     * AbstractStateMachine#AbstractStateMachine(AbstractState,String,boolean)
     * @see AbstractStateMachine#add_listener(AbstractStateMachine_listener)
     */
    public AbstractStateMachine(AbstractState s, String name, boolean show_on_system_out, AbstractStateMachine_listener listener) throws State_exception {
        this(s, name, show_on_system_out);
        if (!_listeners.contains(listener)) {
            _listeners.add(listener);
        }
    }

    /**
     * Doc. required
     */
    public AbstractStateMachine(AbstractState s, String name, boolean show_on_system_out, AbstractStateMachine_listener listener1, AbstractStateMachine_listener listener2) throws State_exception {
        this(s, name, show_on_system_out);
        if (!_listeners.contains(listener1)) {
            _listeners.add(listener1);
        }
        if (!_listeners.contains(listener2)) {
            _listeners.add(listener2);
        }
    }

    /**
     * Internal use only; This method is called during a run-to-completion
     * cycle.
     *
     * @param from
     * @param to
     * @param verbose
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     * @see AbstractStateMachine#run_to_completion(String,boolean)
     */
    protected void activate(AbstractState from, AbstractState to, StringBuffer verbose) throws State_exception {
        AbstractState commonSuper = from.commonSuperWith(to);
        java.util.Stack<AbstractState> state_entry_flow = new java.util.Stack<>();
//        for (AbstractState s = to._node; s != null && s != commonSuper && s._active == false; s = s._node) {
        for (AbstractState s = to; s != null && s != commonSuper && s._active == false; s = s._node) {
            state_entry_flow.push(s);
        }
//        while (!superstates.empty()) {
//            ((AbstractState) superstates.pop()).entry(false);
//        }
        if (!state_entry_flow.empty()) {
//            ((AbstractState) superstates.pop()).entry(false);
            state_entry_flow.pop().entry(state_entry_flow, verbose);
        }
//        to.entry(true);
    }

    /**
     * Internal use only; This method is called during a run-to-completion
     * cycle.
     *
     * @see AbstractStateMachine#run_to_completion(String,boolean)
     */
    protected void disactivate(AbstractState from, AbstractState to, StringBuffer verbose) throws State_exception {
        AbstractState commonSuper = from.commonSuperWith(to);
        to.exit(verbose);
        for (AbstractState s = to; s != commonSuper; s = s._node) {
            if (s.isXorWith(s.brother())) {
                s.brother().exit(verbose);
            }
        }
    }

    /**
     * This method answers the question: is this state the most outer state of
     * the state machine? The answer is always <CODE>true</CODE> for an instance
     * of the <CODE>AbstractStateMachine</CODE> type.
     *
     * @return
     */
    @Override
    public boolean root() {
        return true;
    }

    /**
     * This method amounts to calling <CODE>fires(from,to,true);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to) throws Transition_based_exception {
//        fires(from, to, true);
//    }
    /**
     * This method amounts to calling <CODE>fires(event,from,to,true);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to) throws Transition_based_exception {
        fires(event, from, to, true);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard,null,null);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, boolean guard) throws Transition_based_exception {
//        fires(from, to, guard, null, null);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard,null,null);</CODE>.
     *
     * @param event
     * @param from
     * @param to
     * @param guard
     * @throws com.pauware.pauware_engine.Exceptions.Transition_based_exception
     */
    public void fires(String event, AbstractState from, AbstractState to, boolean guard) throws Transition_based_exception {
        fires(event, from, to, guard, null, null);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard,object,action,null);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, boolean guard, Object object, String action) throws Transition_based_exception {
//        fires(from, to, guard, object, action, null);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard,object,action,null);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, boolean guard, Object object, String action) throws Transition_based_exception {
        fires(event, from, to, guard, object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, boolean guard, Object object, String action, Object[] args) throws Transition_based_exception {
//        fires(from, to, guard, object, action, args, AbstractState.No_reentrance);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, boolean guard, Object object, String action, Object[] args) throws Transition_based_exception {
        fires(event, from, to, guard, object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,null,"true",object,action,args,reentrance_mode);</CODE>
     * if <CODE>guard</CODE> is <CODE>true</CODE>,
     * <CODE>fires(from,to,null,"false",object,action,args,reentrance_mode);</CODE>
     * otherwise.
     */
//    public void fires(AbstractState from, AbstractState to, boolean guard, Object object, String action, Object[] args, byte reentrance_mode) throws Transition_based_exception {
//        if (guard) {
//            fires(from, to, null, "true", object, action, args, reentrance_mode);
//        } else {
//            fires(from, to, null, "false", object, action, args, reentrance_mode);
//        }
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,null,"true",object,action,args,reentrance_mode);</CODE>
     * if <CODE>guard</CODE> is <CODE>true</CODE>,
     * <CODE>fires(event,from,to,null,"false",object,action,args,reentrance_mode);</CODE>
     * otherwise.
     */
    public void fires(String event, AbstractState from, AbstractState to, boolean guard, Object object, String action, Object[] args, byte reentrance_mode) throws Transition_based_exception {
        if (guard) {
            fires(event, from, to, null, "true", null, object, action, args, reentrance_mode);
        } else {
            fires(event, from, to, null, "false", null, object, action, args, reentrance_mode);
        }
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard_object,guard_action,null,null);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action) throws Transition_based_exception {
//        fires(from, to, guard_object, guard_action, null, null);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard_object,guard_action,guard_args,null,null,null);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args) throws Transition_based_exception {
//        fires(from, to, guard_object, guard_action, guard_args, null, null, null);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard_object,guard_action,null,null);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action) throws Transition_based_exception {
        fires(event, from, to, guard_object, guard_action, null, null);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard_object,guard_action,guard_args,null,null,null);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args) throws Transition_based_exception {
        fires(event, from, to, guard_object, guard_action, guard_args, null, null, null);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard_object,guard_action,object,action,null);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object object, String action) throws Transition_based_exception {
//        fires(from, to, guard_object, guard_action, object, action, null);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard_object,guard_action,guard_args,object,action,null);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args, Object object, String action) throws Transition_based_exception {
//        fires(from, to, guard_object, guard_action, guard_args, object, action, null);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard_object,guard_action,object,action,null);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action, Object object, String action) throws Transition_based_exception {
        fires(event, from, to, guard_object, guard_action, object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard_object,guard_action,guard_args,object,action,null);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args, Object object, String action) throws Transition_based_exception {
        fires(event, from, to, guard_object, guard_action, guard_args, object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard_object,guard_action,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object object, String action, Object[] args) throws Transition_based_exception {
//        fires(from, to, guard_object, guard_action, object, action, args, AbstractState.No_reentrance);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(from,to,guard_object,guard_action,guard_args,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, Object[] args) throws Transition_based_exception {
//        fires(from, to, guard_object, guard_action, guard_args, object, action, args, AbstractState.No_reentrance);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard_object,guard_action,null,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action, Object object, String action, Object[] args) throws Transition_based_exception {
        fires(event, from, to, guard_object, guard_action, null, object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(event,from,to,guard_object,guard_action,guard_args,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, Object[] args) throws Transition_based_exception {
        fires(event, from, to, guard_object, guard_action, guard_args, object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method amounts to calling
     * <CODE>fires(Pseudo_event,from,to,guard_object,guard_action,null,object,action,args,reentrance_mode);</CODE>.
     *
     * @see AbstractState#Pseudo_event
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object object, String action, Object[] args, byte reentrance_mode) throws Transition_based_exception {
//        fires(Pseudo_event, from, to, guard_object, guard_action, null, object, action, args, reentrance_mode);
//    }
    /**
     * This method amounts to calling
     * <CODE>fires(Pseudo_event,from,to,guard_object,guard_action,guard_args,object,action,args,reentrance_mode);</CODE>.
     *
     * @see AbstractState#Pseudo_event
     */
//    public void fires(AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, Object[] args, byte reentrance_mode) throws Transition_based_exception {
//        fires(Pseudo_event, from, to, guard_object, guard_action, guard_args, object, action, args, reentrance_mode);
//    }
    /**
     * This method creates and registers (caches) a transition labeled with an
     * event name in a state machine (see: <I>UML State Machine Diagrams</I>
     * formalism).
     *
     * @param event The name of the event labeling the transition
     * @param from The state being the origin of the transition
     * @param to The state being the end of the transition
     * @param guard_object The object in charge of executing the guard (if any)
     * @param guard_action The name of the action to be executed in order to
     * establish the guard's evaluation; This is required * * * * * * * * * * *
     * if <CODE>guard_object</CODE> is used
     * @param guard_args The arguments (if any) of the action which represents
     * the guard
     * @param object The object in charge of executing the action, if any,
     * triggered by the event; Multiple actions require multiple use of this
     * method
     * @param action The name of the action to be executed; This is required *
     * if <CODE>object</CODE> is used
     * @param args The arguments of the action to be executed * * * * * * * * *
     * or <CODE>null</CODE>
     * @param reentrance_mode A flag value which is equal to
     * {@link AbstractState#Reentrance} or {@link AbstractState#No_reentrance};
     * This value enables or disables the reentrance_mode mode of communication
     * for the action to be executed
     * @throws com.pauware.pauware_engine.Exceptions.Transition_based_exception
     */
    synchronized public void fires(String event, AbstractState from, AbstractState to, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, Object[] args, byte reentrance_mode) throws Transition_based_exception {
        if (event == null || from == null || to == null) {
            return;
        }
        if (from.hasForSuperState(to)) {
            throw new Transition_based_exception("Incorrect transition from substate to superstate", from, to);
        }
        if (from.isAndWith(to)) {
            throw new Transition_based_exception("Incorrect transition between orthogonal states", from, to);
        }
        Transition transition = new Transition(from, to);
        AbstractGuard guard = guard(guard_object, guard_action, guard_args);
        java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>> events = _transitions.get(transition);
        java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> guards;
        java.util.Vector<AbstractAction> actions;
        if (events == null) {
            events = new java.util.HashMap<>();
            guards = new java.util.HashMap<>();
            actions = new java.util.Vector<>();
            guards.put(guard, actions);
            events.put(event, guards);
            _transitions.put(transition, events);
        } else {
            guards = events.get(event);
            if (guards == null) {
                guards = new java.util.HashMap<>();
                actions = new java.util.Vector<>();
                guards.put(guard, actions);
                events.put(event, guards);
            } else {
                actions = guards.remove(guard);
                if (actions == null) {
                    actions = new java.util.Vector<>();
                }
                guards.put(guard, actions);
            }
        }
        if (object != null && action != null) {
            AbstractAction new_action = action(object, action, args, reentrance_mode);
            // The following statements rely on a specific implementation of the 'public boolean equals(Object action)' method in the 'AbstractAction' class:
            int index = actions.indexOf(new_action); // Search of first index is safe because duplicates have not been allowed
            if (index != -1 /* && _runtime_events.containsKey(event)*/) {
                actions.setElementAt(new_action, index); // Arguments' update, e.g., 'push' event in 'My_stack' component
            } else {
                actions.addElement(new_action);
            }
        }
    }

    /**
     * This method unregisters a transition labeled with an event name in a
     * state machine (see: <I>UML State Machine Diagrams</I>
     * formalism).
     *
     * @param event The name of the event labeling the transition
     * @param from The state being the origin of the transition
     * @param to The state being the end of the transition
     * @param guard Only simple guards are supported at this time
     * @param object The object in charge of executing the action, if any,
     * triggered by the event; Multiple actions require multiple use of this
     * method
     * @param action The name of the action to be executed; This is required *
     * if <CODE>object</CODE> is used
     * @param args The arguments of the action to be executed * * * * * * * * *
     * or <CODE>null</CODE>
     * @param reentrance_mode A flag value which is equal to
     * {@link AbstractState#Reentrance} or {@link AbstractState#No_reentrance};
     * This value enables or disables the reentrance_mode mode of communication
     * for the action to be executed
     * @throws com.pauware.pauware_engine.Exceptions.Transition_based_exception
     */
    synchronized public void unfires(String event, AbstractState from, AbstractState to, boolean guard, Object object, String action, Object[] args, byte reentrance_mode) throws Transition_based_exception {
        if (event == null || from == null || to == null) {
            return;
        }
        Transition t = new Transition(from, to);
        java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>> events = _transitions.get(t);
        if (events != null) {
            java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> guards = events.get(event);
            if (guards != null) {
                AbstractGuard g = guard(null, guard ? "true" : "false", null);
                java.util.Vector<AbstractAction> actions = guards.get(g);
                if (actions != null && object != null && action != null) {
                    AbstractAction a = action(object, action, args, reentrance_mode);
                    int index = actions.indexOf(a);
                    if (index != -1) {
                        actions.removeElementAt(index);
                        if (actions.isEmpty()) {
                            guards.remove(g);
                            if (guards.isEmpty()) {
                                _transitions.remove(t);
                            }
                        }
                    }
                } else {
                    guards.remove(g);
                    if (guards.isEmpty()) {
                        _transitions.remove(t);
                    }
                }
            }
        }
    }

    /**
     * This method amounts to calling
     * <CODE>run_to_completion(Pseudo_event);</CODE>.
     *
     * @see AbstractState#Pseudo_event
     */
//    public void run_to_completion() throws State_exception {
//        run_to_completion(Pseudo_event);
//    }
    /**
     * This method amounts to calling
     * <CODE>run_to_completion(event,AbstractStateMachine.Don_t_compute_invariants);</CODE>
     *
     * @param event
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    public void run_to_completion(String event) throws State_exception {
        run_to_completion(event, AbstractStateMachine.Don_t_compute_invariants);
    }

    /**
     * This method is the key mechanism of the <I>PauWare</I> software; It moves
     * a state machine from one stable consistent context to another.
     *
     * @param event The name of the event to be processed (<CODE>null</CODE>
     * means no effect); Only transitions labeled and registered with this event
     * name are processed (see: the
     * {@link AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)}
     * method)
     * @param compute_invariants This mode triggers the calculation of
     * invariants
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    synchronized public void run_to_completion(String event, boolean compute_invariants) throws State_exception {
        if (event == null) {
            return;
        }
        _eliminated_transitions.removeAllElements();
        _execution.clear();
        _verbose.delete(0, _verbose.length());
        _verbose.append(_name + '.' + event + "\n");

        // Review of all transitions:
        for (java.util.Iterator<Transition> i = _transitions.keySet().iterator(); i.hasNext();) {
            Transition transition = i.next();
            AbstractState from = transition._from;
            AbstractState to = transition._to;
            java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>> events = _transitions.get(transition);
            java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> cached_guards = events.get(event);
            java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> runtime_guards = events.get(Pseudo_event);
            if (cached_guards == null) {
                if (runtime_guards != null) {
                    cached_guards = new java.util.HashMap<>();
                    for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> entry : runtime_guards.entrySet()) {
                        cached_guards.put(entry.getKey(), entry.getValue());
                    }
                    events.put(event, cached_guards);
                    events.remove(Pseudo_event);
                }
            } else {
                if (runtime_guards != null) {
                    if (cached_guards != runtime_guards) { // i.e., ! event.equals(Pseudo_event)
                        for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> entry : runtime_guards.entrySet()) {
                            AbstractGuard runtime_guard = entry.getKey();
                            java.util.Vector<AbstractAction> runtime_actions = runtime_guards.get(runtime_guard);
                            java.util.Vector<AbstractAction> cached_actions = cached_guards.remove(runtime_guard);
                            for (int j = 0; cached_actions != null && j < cached_actions.size(); j++) {
                                AbstractAction cached_action = cached_actions.elementAt(j);
                                if (!runtime_actions.contains(cached_action)) {
                                    runtime_actions.addElement(cached_action);
                                }
                            }
                            cached_guards.put(runtime_guard, runtime_actions);
                        }
                    } else {
                        cached_guards = new java.util.HashMap<>();
                        for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> runtime_guard : runtime_guards.entrySet()) {
                            cached_guards.put(runtime_guard.getKey(), runtime_guard.getValue());
                        }
                    }
                    // events.put(event,cached_guards); unuseful since 'event.equals(Pseudo_event) == true'
                    events.remove(Pseudo_event);
                }
            }

            if (cached_guards != null) {
                java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> new_cached_guards = new java.util.HashMap<>();
                for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> entry : cached_guards.entrySet()) {
                    AbstractGuard guard = entry.getKey();
                    try {
                        if (guard.execute()) {
                            java.util.Vector<AbstractAction> executable_actions = cached_guards.get(guard);
                            if (from._active) {
                                if (_execution.containsKey(transition)) {
                                    throw new Transition_based_exception("\t{ERROR}Two conflicting eligible transitions with same source and target states", from, to);
                                } else {
                                    _verbose.append("\tELIGIBLE TRANSITION: " + from._name + " -> " + to._name + ", guard: [" + guard.verbose() + "]\n");
                                    // actions associated with 'guard.execute() == true' are going to be executed:
                                    Object[] executed_actions = new Object[executable_actions.size()];
                                    executable_actions.copyInto(executed_actions);
                                    _execution.put(transition, executed_actions);
                                }
                            }
                            // runtime guards whose value is 'true' are going to be incorporated into the cache with value 'false':
                            if (runtime_guards != null && runtime_guards.containsKey(guard)) {
                                guard._value = false;
                                java.util.Vector<AbstractAction> cached_actions = cached_guards.get(guard);
                                if (cached_actions != null) {
                                    if (cached_actions != executable_actions) {
                                        for (int j = 0; j < cached_actions.size(); j++) {
                                            AbstractAction cached_action = cached_actions.elementAt(j);
                                            if (!executable_actions.contains(cached_action)) {
                                                executable_actions.addElement(cached_action);
                                            }
                                        }
                                    }
                                    new_cached_guards.put(guard, executable_actions);
                                } else {
                                    if (executable_actions != null) {
                                        new_cached_guards.put(guard, executable_actions);
                                    } else {
                                        new_cached_guards.put(guard, new java.util.Vector<>());
                                    }
                                }
                            } else {
                                new_cached_guards.put(guard, executable_actions);
                            }
                        } else {
                            // 'guard.execute() == false'
                            if (!new_cached_guards.containsKey(guard)) {
                                new_cached_guards.put(guard, cached_guards.get(guard));
                            }
                        }
                    } catch (State_exception se) {
                        _verbose.append("\t{WARNING}canceled: " + from._name + " -> " + to._name + ", guard: [" + guard.verbose() + "]\n");
                    }
                }
                events.put(event, new_cached_guards);
            }
        }

        // Eliminated transitions resulting from event overriding:
        for (java.util.Iterator<Transition> i = _execution.keySet().iterator(); i.hasNext();) {
            Transition transition = i.next();
            AbstractState from = transition._from;
            AbstractState to = transition._to;
            for (java.util.Iterator<Transition> j = i; j.hasNext();) {
                // if (j != i) {
                Transition other_transition = j.next();
                AbstractState other_from = other_transition._from;
                AbstractState other_to = other_transition._to;
                if (from == other_from) {
                    if (to != other_to && !to.isAndWith(other_to) || (!to.hasForSuperState(from) || !other_to.hasForSuperState(from))) {
                        throw new Transition_based_exception("\t{ERROR}Two conflicting triggerable transitions\n\tFirst transition" + ": 'from' is " + from.name() + " - 'to' is " + to.name() + "\n\tSecond transition", other_from, other_to);
                    }
                } else {
                    if (from.hasForSuperState(other_from) && !_eliminated_transitions.contains(other_transition) && !to.isAndWith(other_to)) {
                        _verbose.append("\t{WARNING}overridden: " + other_from._name + " -> " + other_to._name + ", guard: [true]\n");
                        _eliminated_transitions.addElement(other_transition);
                    }
                    if (other_from.hasForSuperState(from) && !_eliminated_transitions.contains(transition) && !other_to.isAndWith(to)) {
                        _verbose.append("\t{WARNING}overridden: " + from._name + " -> " + to._name + ", guard: [true]\n");
                        _eliminated_transitions.addElement(transition);
                    }
                }
                // }
            }
        }

        // start of execution, execution of allowed events:
        allowedEvent(event, _execution, _eliminated_transitions, _verbose);
        for (int i = 0; i < _eliminated_transitions.size(); i++) {
            _execution.remove(_eliminated_transitions.elementAt(i));
        }

        // execution of 'exit' actions:
        try {
            for (java.util.Iterator<Transition> i = _execution.keySet().iterator(); i.hasNext();) {
                Transition transition = i.next();
                disactivate(transition._from, transition._to, _verbose);
            }
        } catch (State_exception se) {
            _verbose.append("\t\t{SEVERE} " + se.getMessage() + "\n");
        }

        // execution of actions associated with events:
        for (java.util.Iterator<Transition> i = _execution.keySet().iterator(); i.hasNext();) {
            Transition transition = i.next();
            Object[] executed_actions = _execution.get(transition);
            for (int j = 0; j < executed_actions.length; j++) {
                AbstractAction executed_action = (AbstractAction) executed_actions[j];
                try {
                    executed_action.execute();
                    _verbose.append("\t\tEXECUTED ACTION: ");
                } catch (State_exception se) {
                    _verbose.append("\t\t{WARNING}action failed: " + "\n");
                } finally {
                    _verbose.append(executed_action.verbose() + "\n");
                }
            }
        }

        // execution of 'entry' actions followed by 'do' activities:
        try {
            for (java.util.Iterator<Transition> i = _execution.keySet().iterator(); i.hasNext();) {
                Transition transition = i.next();
                activate(transition._from, transition._to, _verbose);
            }
        } catch (State_exception se) {
            _verbose.append("\t\t{SEVERE}" + se.getMessage() + "\n");
        }

        // Finalization:
        _current_state = current_state();
        _verbose.append("\tCURRENT STATE: " + _current_state);

        // Evaluation of invariants:
        if (compute_invariants) {
            try {
                _verbose.append(deepStateInvariant());
            } catch (State_exception se) {
                _verbose.append(se.getMessage());
            } finally {
                _verbose.append("\n");
            }
        }
        try {
            for (AbstractStateMachine_listener listener : _listeners) {
                listener.run_to_completion(_verbose.toString(), (java.util.HashMap<Transition, Object[]>) _execution);
            }
        } catch (Exception e) {
            throw new State_exception(e.getMessage());
        }
        if (_show_on_system_out) {
            System.out.println(_verbose);
        }
    }

    /**
     * This method launches a state machine.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    synchronized public void start() throws State_exception {
        try {
            for (AbstractStateMachine_listener listener : _listeners) {
                listener.post_construct(this);
            }
            _verbose.delete(0, _verbose.length());
            _verbose.append("***START***\n");
            entry(null, _verbose);
            if (_show_on_system_out) {
                System.out.println(_verbose);
            }
            for (AbstractStateMachine_listener listener : _listeners) {
                listener.start(_verbose.toString());
            }
        } catch (Exception e) {
            throw new State_exception(e.getMessage());
        }
    }

    /**
     * This method stops a state machine.
     *
     * @throws com.pauware.pauware_engine.Exceptions.State_exception
     */
    synchronized public void stop() throws State_exception {
        try {
            _verbose.delete(0, _verbose.length());
            _verbose.append("***STOP***\n");
            exit(_verbose);
            if (_show_on_system_out) {
                System.out.println(_verbose);
            }
            for (AbstractStateMachine_listener listener : _listeners) {
                listener.stop(_verbose.toString());
                listener.pre_destroy();
            }
        } catch (Exception e) {
            throw new State_exception(e.getMessage());
        }
    }

    /**
     * Management facility: this method is offered by a software component,
     * which implements the
     * {@link com.pauware.pauware_engine.Core.Manageable_base} interface.
     */
    @Override
    public String async_current_state() {
        return _current_state;
    }

    /**
     * Management facility: this method is offered by a software component,
     * which implements the <CODE>Manageable_base</CODE> interface.
     *
     * @see com.pauware.pauware_engine.Core.Manageable_base
     */
    @Override
    public boolean in_state(String name) {
        String current_state = async_current_state();
        if (_Undefined.equals(current_state)) {
            current_state = current_state();
        }
        return current_state.indexOf(_Left_parenthesis + name) != -1 || current_state.indexOf(_AND + name) != -1;
    }

    /**
     * Management facility: this method is offered by a software component,
     * which implements the
     * {@link com.pauware.pauware_engine.Core.Manageable_base} interface.
     */
    @Override
    synchronized public void to_state(String name) throws State_exception {
        AbstractState to = lookup(name);
        if (to != null) {
            try {
                _verbose.delete(0, _verbose.length());
                _verbose.append("***TO STATE: " + name + "***\n");
                disactivate(this, to, _verbose);
                if (_show_on_system_out) {
                    System.out.println(_verbose);
                }
                for (AbstractStateMachine_listener listener : _listeners) {
                    listener.stop(_verbose.toString());
                }
                _verbose.delete(0, _verbose.length());
                _verbose.append("***RESTART FROM: " + name + "***\n");
                activate(this, to, _verbose);
                if (_show_on_system_out) {
                    System.out.println(_verbose);
                }
                for (AbstractStateMachine_listener listener : _listeners) {
                    listener.start(_verbose.toString());
                }
            } catch (Exception e) {
                throw new State_exception(e.getMessage());
            }
        }
    }

    /**
     * Management facility: this method is offered by a software component,
     * which implements the
     * {@link com.pauware.pauware_engine.Core.Manageable_base} interface.
     */
    @Override
    public String verbose() {
        return _verbose.toString();
    }

    /**
     * This method is used when an {@link AbstractStateMachine} is put in a map
     * data structure as a key.
     *
     * @param s
     */
    @Override
    public boolean equals(Object s) {
        if (this == s) {
            return true;
        }
        if (s instanceof AbstractStateMachine) {
            return _name.equals(((AbstractStateMachine) s)._name);
        }
        return false;
    }

    /**
     * This method is used when an {@link AbstractStateMachine} is put in a map
     * data structure as a key.
     */
    public int hashCode() {
        return _name.hashCode();
    }

    /**
     * For visualization purposes only: this method may be used by the
     * <I>PauWare2Web</I> tool.
     *
     * @param event
     * @param transition
     * @param guard
     * @return
     */
    public String to_UML(String event, Transition transition, AbstractGuard guard) {
        if (event == null || transition == null || guard == null) {
            return "";
        }
        StringBuffer label = new StringBuffer("");
        java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>> events = _transitions.get(transition);
        if (events != null) {
            java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> guards = events.get(event);
            if (guards != null) {
                label.append(event);
                java.util.Vector<AbstractAction> actions = guards.get(guard);
                if (actions != null) {
                    String guard_to_UML = guard.to_UML();
                    if (guard_to_UML != null) {
                        label.append("[" + guard_to_UML + "]");
                    }
                    label.append("/");
                    java.util.Enumeration<AbstractAction> e = actions.elements();
                    while (e.hasMoreElements()) {
                        String action_to_UML = e.nextElement().to_UML();
                        if (action_to_UML != null) {
                            label.append(action_to_UML + ",");
                        }
                    }
                    label.deleteCharAt(label.length() - 1);
                }
            }
        }
        return label.toString();
    }

    /**
     * For visualization purposes only: this method is used by the
     * <I>PauWare2Web</I> tool to compute the state machine's visualization
     * graph.
     *
     * @return
     * @since ver. 1.3
     */
    public String to_PlantUML() {
        return _compute_state_views(_compute_transition_views(new java.util.HashMap<>()));
    }

    private String _compute_event_view(String event, Transition transition, String transition_view) {
        String event_view = "";
        java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> guarded_actions = _transitions.get(transition).get(event);
        for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> entry : guarded_actions.entrySet()) {
            AbstractGuard guard = entry.getKey();
            event_view += transition_view + guard.to_UML();
            if (!(guarded_actions.get(guard)).isEmpty()) {
                event_view += '/';
            }
            for (java.util.Enumeration<AbstractAction> action_elements = guarded_actions.get(guard).elements(); action_elements.hasMoreElements();) {
                AbstractAction action = action_elements.nextElement();
                event_view += action.to_UML() + String.valueOf(Sequence_character);
            }
            if (event_view.charAt(event_view.length() - 1) == Sequence_character) {
                event_view = event_view.substring(0, event_view.lastIndexOf(Sequence_character));
            }
            event_view += Textual_view_subject_separator + transition.hashCode() + Textual_view_subject_separator + "\n";
        }
        return event_view;
    }

    /**
     * For visualization purposes only: this method is internally called by the
     * {@link AbstractStateMachine#to_PlantUML()} method to compute the
     * transition views of the state machine.
     *
     * @since ver. 1.3
     */
    private java.util.Map<String, java.util.Set<String>> _compute_transition_views(java.util.Map<String, java.util.Set<String>> transition_views) {
        for (java.util.Iterator<Transition> i = _transitions.keySet().iterator(); i.hasNext();) {
            Transition transition = i.next();
            AbstractState from = transition.from();
            String from_name = Clean_up(from.name());
            String from_ancestor_name = Clean_up(from.ancestor().name());
            AbstractState to = transition.to();
            String to_name = Clean_up(to.name());
            String to_ancestor_name = Clean_up(to.ancestor().name());
            java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>> events = _transitions.get(transition);

            String transition_view = "";

            for (java.util.Iterator<String> j = events.keySet().iterator(); j.hasNext();) {
                String event = j.next();
                /**
                 * PlantUML transition that crosses over an orthogonal region
                 */
                _Cross_over cross_over = to._cross_over(from);
                if (cross_over == _Cross_over.DESCENDING) {
                    /**
                     * On place "<<entryPoint>>" sur 'to.ancestor()' sous
                     * réserve que 'to' *NE SOIT PAS* un état concurrent dans
                     * 'to.ancestor()'
                     */
                    if (to._cross_over(to.ancestor()) == _Cross_over.NONE) { // e.g., 'Busy' -> 'S12', 'Busy' -> 'S21', 'Busy' -> 'S22'
                        transition_view = AbstractState._PlantUML_state + "\""
                                + from_name + "→\" as " + from_name + _PlantUML_state_entry_point + to_name + _PlantUML_entry_point
                                + from_name + _PlantUML_state_entry_point + to_name + _PlantUML_transition_ + to_name + _PlantUML_transition_execution + Textual_view_subject_separator
                                + Clean_up(event);

                    } else { // On crée un état pour dessiner la transition :
                        transition_view = AbstractState._PlantUML_state + "\""
                                + from_name + "→\" as " + from_name + _PlantUML_state_exit_point + to_name + "\n"
                                + from_name + _PlantUML_state_exit_point + to_name + " #magenta " + _PlantUML_transition_ + to_name + _PlantUML_transition_execution + Textual_view_subject_separator
                                + Clean_up(event);
                    }
                    if (transition_views.get(to_ancestor_name) == null) {
                        transition_views.put(to_ancestor_name, new java.util.LinkedHashSet<>());
                    }
                    transition_views.get(to_ancestor_name).add(_compute_event_view(event, transition, transition_view));
                } else {
                    if (cross_over == _Cross_over.ASCENDING) {
                        throw new UnsupportedOperationException("PauWare2 does not support '_Cross_over.ASCENDING'");
                    } else {
                        if (cross_over == _Cross_over.SPAN) {
                            /**
                             * On place "<<entryPoint>>" sur 'to.ancestor()'
                             * sous réserve que 'to' *NE SOIT PAS* un état
                             * concurrent dans 'to.ancestor()'
                             */
                            if (to._cross_over(to.ancestor()) == _Cross_over.NONE) { // e.g., 'S21' -> 'Idle', 'Route_plan_development' -> 'Route_for_fire_trucks_fixed'
                                transition_view = AbstractState._PlantUML_state + "\""
                                        + from_name + "→\" as " + from_name + _PlantUML_state_entry_point + to_name + _PlantUML_entry_point
                                        + from_name + _PlantUML_state_entry_point + to_name + _PlantUML_transition_ + to_name + _PlantUML_transition_execution + Textual_view_subject_separator
                                        + Clean_up(event);

                            } else { // e.g., 'Programmable_thermostat'-> 'Run' (on crée un état pour dessiner la transition) :
                                transition_view = AbstractState._PlantUML_state + "\""
                                        + from_name + "→\" as " + from_name + _PlantUML_state_entry_point + to_name + "\n"
                                        + from_name + _PlantUML_state_entry_point + to_name + " #magenta " + _PlantUML_transition_ + to_name + _PlantUML_transition_execution + Textual_view_subject_separator
                                        + Clean_up(event);
                            }
                            if (transition_views.get(to_ancestor_name) == null) {
                                transition_views.put(to_ancestor_name, new java.util.LinkedHashSet<>());
                            }
                            transition_views.get(to_ancestor_name).add(_compute_event_view(event, transition, transition_view));
                            /**
                             * On place "<<exitPoint>>" sur 'from.ancestor()'
                             * sous réserve que 'from' *NE SOIT PAS* un état
                             * concurrent dans 'from.ancestor()'
                             */
                            if (from._cross_over(from.ancestor()) == _Cross_over.NONE) { // e.g., 'S21' -> 'Idle', 'Route_plan_development' -> 'Route_for_fire_trucks_fixed'
                                transition_view = AbstractState._PlantUML_state + "\"→"
                                        + to_name + "\" as " + from_name + _PlantUML_state_exit_point + to_name + "\n"
                                        + from_name + _PlantUML_transition_ + from_name + _PlantUML_state_exit_point + to_name + _PlantUML_exit_point + _PlantUML_transition_execution + Textual_view_subject_separator
                                        + Clean_up(event);
                            } else {
                                transition_view = AbstractState._PlantUML_state + "\""
                                        + from_name + "→\" as " + from_name + _PlantUML_state_exit_point + to_name + "\n"
                                        + from_name + _PlantUML_state_exit_point + to_name + " #magenta " + _PlantUML_transition_ + to_name + _PlantUML_transition_execution + Textual_view_subject_separator
                                        + Clean_up(event);
                            }
                            if (transition_views.get(from_ancestor_name) == null) {
                                transition_views.put(from_ancestor_name, new java.util.LinkedHashSet<>());
                            }
                            transition_views.get(from_ancestor_name).add(_compute_event_view(event, transition, transition_view));
                        } else {
                            if (cross_over == _Cross_over.NONE) {
                                transition_view = from_name + _PlantUML_transition + to_name + _PlantUML_transition_execution + Textual_view_subject_separator + Clean_up(event);
                                AbstractState commonSuper = from.commonSuperWith(to);
                                if (commonSuper == from) { // 'Guard_conflict.PauWare2Web': 'Y --> S1'
                                    if (transition_views.get(from_name) == null) {
                                        transition_views.put(from_name, new java.util.LinkedHashSet<>());
                                    }
                                    transition_views.get(from_name).add(_compute_event_view(event, transition, transition_view));
                                } else {
                                    if (commonSuper == to) {
                                        if (transition_views.get(to_ancestor_name) == null) {
                                            transition_views.put(to_ancestor_name, new java.util.LinkedHashSet<>());
                                        }
                                        transition_views.get(to_ancestor_name).add(_compute_event_view(event, transition, transition_view));
                                    } else { // Common situations:
                                        if (from == to) { // 'S31 --> S31', 'Fire_trucks_arriving --> Fire_trucks_arriving'...
                                            if (transition_views.get(from_name) == null) {
                                                transition_views.put(from_name, new java.util.LinkedHashSet<>());
                                            }
                                            transition_views.get(from_name).add(_compute_event_view(event, transition, transition_view));
                                        } else {
                                            if (transition_views.get(from_ancestor_name) == null) {
                                                transition_views.put(from_ancestor_name, new java.util.LinkedHashSet<>());
                                            }
                                            transition_views.get(from_ancestor_name).add(_compute_event_view(event, transition, transition_view));
                                        }
                                    }
                                }
                            } else {
//                                assert (cross_over == _Cross_over.WEIRD);
                                // 'Guard_conflict.PauWare2Web': 'Y --> S1'
                                transition_view = AbstractState._PlantUML_state + "\""
                                        + from_name + "→\" as " + from_name + _PlantUML_state_exit_point + to_name + "\n"
                                        + from_name + _PlantUML_state_exit_point + to_name + " #magenta " + _PlantUML_transition_ + to_name + _PlantUML_transition_execution + Textual_view_subject_separator
                                        + Clean_up(event);
                                if (transition_views.get(from_name) == null) {
                                    transition_views.put(from_name, new java.util.LinkedHashSet<>());
                                }
                                transition_views.get(from_name).add(_compute_event_view(event, transition, transition_view));
                            }
                        }
                    }
                }
            }
        }
        return transition_views;
    }
}
