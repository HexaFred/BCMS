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
 * This abstract class represents the general notion of <I>State</I> in UML. Use
 * of this class occurs through the following subclass:
 * {@link com.pauware.pauware_engine.Core.State}. Since <I>UML State Machine
 * Diagrams</I> are nested, an instance of this class may be a leaf state or a
 * state containing substates.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
abstract public class AbstractState {

    /**
     * This class variable defines the sign for <I>computing sequence</I>.
     *
     * @since ver. 1.3
     */
    public static final char Sequence_character = ';';

    /**
     * This class variable is a constant value which is used for the naming of
     * states when state machines are composed.
     *
     * @since ver. 1.2
     */
    public static final String State_name_separator = "::";
    /**
     * This class variable is a constant value. It represents the fact that an
     * event sending IS NOT the cause of reentrance. This is the default mode in
     * PauWare.
     *
     * @see
     * com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)
     */
    public static final byte No_reentrance = -128;
    /**
     * This class variable is a constant value. It represents the fact that an
     * event sending IS the cause of reentrance. For an event sender (state
     * machine), using this constant value avoids receiving destabilizing events
     * while it has not completed its run-to-completion cycle.
     *
     * @see
     * com.pauware.pauware_engine.Core.AbstractStateMachine#fires(String,AbstractState,AbstractState,Object,String,Object[],Object,String,Object[],byte)
     */
    public static final byte Reentrance = -127;
    /**
     * This class variable is a constant value (<CODE>"completion"</CODE>),
     * which is used for transitions that do not have a labeling event. As a
     * result, events <b>must not</b> be named <CODE>"completion"</CODE>.
     */
    public static final String Completion = "completion";
    /**
     * This class variable is a constant value (<CODE>"pseudo-event"</CODE>),
     * which is used to name events by default. As a result, events <b>must
     * not</b> be named <CODE>"pseudo-event"</CODE>.
     */
    public static final String Pseudo_event = "pseudo-event";
    /**
     * This class variable is a constant value ( <CODE>"pseudo-state"</CODE>)
     * which is used to name states by default. As a result, states <b>must
     * not</b> be named <CODE>"pseudo-state"</CODE>. Note: fictitious states are
     * created when states are composed together by means of the <I>nesting</I>,
     * <I>xor</I>
     * and <I>and</I> operators. If they are not named, these states have no
     * "true existence" in <I>UML State Machine Diagrams</I>. They consequently
     * just play the role of "composites" (a.k.a. "regions" in UML) in order to
     * properly organize the state machine within the memory.
     *
     * @see AbstractState#name(String)
     */
    public static final String Pseudo_state = "pseudo-state";
    /**
     * This field records if a state is active.
     *
     * @see AbstractState#active()
     */
    protected boolean _active = false;
    /**
     * This field records the activity, if any, associated with a state.
     *
     * @see AbstractState#doActivity(Object,String,Object[])
     */
    protected AbstractAction _do = null;
    /**
     * This field records the action(s), if any, associated with a state when
     * entering it.
     *
     * @see AbstractState#set_entryAction(Object,String,Object[],byte)
     * @see AbstractState#reset_entryAction(Object,String,Object[],byte)
     */
    protected final java.util.Vector<AbstractAction> _entry = new java.util.Vector<>();
    /**
     * This field records the action(s), if any, associated with a state when
     * exiting it.
     *
     * @see AbstractState#set_exitAction(Object,String,Object[],byte)
     * @see AbstractState#reset_exitAction(Object,String,Object[],byte)
     */
    protected final java.util.Vector<AbstractAction> _exit = new java.util.Vector<>();
    /**
     * This field records the allowed events of a state with their possible
     * associated guards and their respective associated actions. Note: by
     * definition, allowed events <b>bypass</b> <I>entry</I> and <I>exit</I>
     * actions.
     *
     * @see
     * AbstractState#allowedEvent(String,Object,String,Object[],Object,String,Object[],byte)
     */
    protected final java.util.Map<String, java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>>> _allowed_events = new java.util.HashMap<>();
    /**
     * This field records the invariant, if any, associated with a state.
     *
     * @see AbstractState#stateInvariant(Object,String,Object[])
     */
    protected AbstractAction _invariant = null;
    /**
     * This field records if a state is an input state.
     *
     * @see AbstractState#inputState()
     */
    protected boolean _inputState = false;
    /**
     * This field records if a state is an output state.
     *
     * @see AbstractState#outputState()
     */
    protected boolean _outputState = false;
    /**
     * This field records the name of a state. The default name is
     * <CODE>Pseudo_state</CODE>.
     *
     * @see AbstractState#Pseudo_state
     */
    protected String _name = Pseudo_state;
    /**
     * This field organizes a state machine as a binary tree. This field may be
     * left <CODE>null</CODE> (meaning that the state is a leaf state). In such
     * a case, one must have <CODE>_right == null</CODE>.
     */
    protected AbstractState _left = null;
    /**
     * This field organizes a state machine as a binary tree. If the type of the
     * state owning this field is <CODE>AbstractState</CODE>, this field cannot
     * be left <CODE>null</CODE> (meaning that the state is a direct or indirect
     * substate of the state machine itself). Otherwise if this state is an
     * instance of <CODE>AbstractStateMachine</CODE>, this field must be equal
     * to <CODE>null</CODE>.
     */
    protected AbstractState _node = null;
    /**
     * This field organizes a state machine as a binary tree. This field may be
     * left <CODE>null</CODE> (meaning that the state is a leaf state). In such
     * a case, one must have <CODE>_left == null</CODE>.
     */
    protected AbstractState _right = null;
    /**
     * This field records if the two direct substates of this state are
     * exclusive or orthogonal.
     *
     * @see AbstractState#xor(AbstractState)
     * @see AbstractState#and(AbstractState)
     */
    protected boolean _xor = false;

    /**
     * Internal use only; This constructor creates a state. This constructor is
     * imposed by the fact that <CODE>State</CODE> objects are serializable.
     *
     * @see com.pauware.pauware_engine.Core.State
     */
    protected AbstractState() {
    }

    /**
     * Internal use only; This constructor creates a state.
     *
     * @param name If name is equal to <CODE>null</CODE>, the name of the state
     * is already set to <CODE>Pseudo_state</CODE>.
     *
     * @see AbstractState#Pseudo_state
     */
    protected AbstractState(String name) {
        if (name != null) {
            _name = name;
        }
    }

    /**
     * Implementation of this method occurs in
     * {@link com.pauware.pauware_engine.Core.State}.
     *
     * @see com.pauware.pauware_engine.Core.Action
     */
    abstract protected AbstractAction action(Object object, String action, Object[] args, byte reentrance_mode);

    /**
     * Implementation of this method occurs in
     * {@link com.pauware.pauware_engine.Core.State}.
     *
     * @see com.pauware.pauware_engine.Core.Action
     */
    abstract protected AbstractAction activity(Object object, String action, Object[] args);

    /**
     * Implementation of this method occurs in
     * {@link com.pauware.pauware_engine.Core.State}.
     *
     * @see com.pauware.pauware_engine.Core.Guard
     */
    abstract protected AbstractGuard guard(Object guard_object, String guard_action, Object[] guard_args);

    /**
     * This method aims at managing nesting.
     *
     * @return
     */
    abstract protected AbstractState createFather();

    /**
     * This method makes two states exclusive. States which are already linked
     * to other states (by nesting, exclusion or orthogonality relationships)
     * cannot be involved in this method. <I>PauWare</I> propagates nesting,
     * exclusion and orthogonality relationships through states by means of
     * transitivity.
     *
     * @see AbstractState#hasForSuperState(AbstractState)
     * @see AbstractState#isAndWith(AbstractState)
     * @see AbstractState#isXorWith(AbstractState)
     * @return A new state being the direct superstate of the two XOR-based
     * linked states. This new state may be named by using the
     * {@link AbstractState#name(String)} method; Otherwise it is a pseudo-state
     * (see {@link AbstractState#Pseudo_state})
     * @throws State_based_exception A XOR error (concerning the two
     * incriminated states) if the exclusion link cannot be operated.
     */
    public AbstractState xor(AbstractState s) throws State_based_exception {
        if (s == null || s._node != null || this._node != null) {
            throw new State_based_exception("XOR error", this, s);
        }
        AbstractState node = createFather();
        if (this._name.hashCode() < s._name.hashCode()) {
            node._left = this;
            node._right = s;
        } else {
            node._left = s;
            node._right = this;
        }
        this._node = node;
        s._node = node;
        node._xor = true;
        if (Pseudo_state.equals(_name) && this._xor) {
            this._inputState = this._left._inputState | this._right._inputState;
        }
        if (Pseudo_state.equals(s._name) && s._xor) {
            s._inputState = s._left._inputState | s._right._inputState;
        }
        return node;
    }

    /**
     * This method makes the two argument states orthogonal. States which are
     * already linked to other states (by nesting, exclusion or orthogonality
     * relationships) cannot be involved in this method. <I>PauWare</I>
     * propagates nesting, exclusion and orthogonality relationships through
     * states by means of transitivity.
     *
     * @see AbstractState#hasForSuperState(AbstractState)
     * @see AbstractState#isAndWith(AbstractState)
     * @see AbstractState#isXorWith(AbstractState)
     * @return A new state being the direct superstate of the two AND-based
     * linked states. This new state may be named by using the
     * {@link AbstractState#name(String)} method; Otherwise it is a pseudo-state
     * (see {@link AbstractState#Pseudo_state})
     * @throws State_based_exception An AND error with the two incriminated
     * states if the orthogonality link cannot be operated
     */
    public AbstractState and(AbstractState s) throws State_based_exception {
        if (s == null || s._node != null || this._node != null) {
            throw new State_based_exception("AND error", this, s);
        }
        AbstractState node = createFather();
        if (this._name.hashCode() < s._name.hashCode()) {
            node._left = this;
            node._right = s;
        } else {
            node._left = s;
            node._right = this;
        }
        this._node = node;
        s._node = node;
        node._xor = false;
        return node;
    }

    /**
     * This method makes a state and a state machine orthogonal.
     *
     * @since ver. 1.2
     */
    public AbstractStateMachine and(AbstractStateMachine s) throws State_based_exception {
        return (AbstractStateMachine) s.and(this);
    }

    /**
     * This method indicates if a state is active.
     */
    public boolean active() {
        return _active;
    }

    /**
     * Internal use only; This method computes the "brother" state, if any, of a
     * state. If the type of the state is <CODE>AbstractState</CODE>, the
     * returned value cannot be <CODE>null</CODE>.
     *
     * @return The "brother" state or <CODE>null</CODE> if this state is an
     * instance of <CODE>AbstractStateMachine</CODE> (<I>i.e.</I>, an instance
     * of a state machine).
     */
    protected AbstractState brother() {
        if (_node == null) {
            return null;
        }
        return this == _node._left ? _node._right : _node._left;
    }

    /**
     * Internal use only; This method computes the closest superstate of two
     * states. The common superstate may be the state machine itself
     * (<I>i.e.</I>, an instance of {@link AbstractStateMachine}).
     */
    protected AbstractState commonSuperWith(AbstractState s) {
        if (s == null) {
            return null;
        }
        if (s == this) {
            return _node;
        }
        java.util.ArrayList<AbstractState> supers = new java.util.ArrayList<>();
        for (AbstractState i = this; i != null; i = i._node) {
            supers.add(i);
        }
        AbstractState commonSuperState = s;
        while (commonSuperState != null && !supers.contains(commonSuperState)) {
            commonSuperState = commonSuperState._node;
        }
        return commonSuperState;
    }

    /**
     * Internal use only; This method computes the closest <b>non-anonymous</b>
     * superstate of a state. This superstate may be the state machine itself
     * (<I>i.e.</I>, an instance of {@link AbstractStateMachine}).
     */
    protected AbstractState ancestor() {
        if (this._node == null) { // State machine itself...
            return this;
        }
        AbstractState ancestor = this._node;
        while (Pseudo_state.equals(ancestor._name)) {
            ancestor = ancestor._node;
        }
        return ancestor;
    }

    /**
     * This method indicates whether the argument state is a direct or indirect
     * superstate of <CODE>this</CODE>.
     */
    public boolean hasForSuperState(AbstractState s) {
        if (s == null) {
            return false;
        }
        if (s == _node) {
            return true;
        }
        return _node != null ? _node.hasForSuperState(s) : false;
    }

//    /**
//     * This method indicates if two states are orthogonal (shallow).
//     *
//     * @param s The state for which orthogonality is established or not.
//     * @return
//     */
//    public boolean isAndWith(AbstractState s) { // Ver 1.3 with tiny bug...
//        if (s == null || s == this || s.hasForSuperState(this) || this.hasForSuperState(s)) {
//            return false;
//        }
//        AbstractState commonSuper = this.commonSuperWith(s);
//        if (commonSuper == null) {
//            return false;
//        }
//        return (commonSuper._xor == false);
//    }
    /**
     * This method indicates if two states are orthogonal (deep).
     *
     * @param s The state for which orthogonality is established or not.
     * @return
     */
    public boolean isAndWith(AbstractState s) {
        if (s == null || s == this || s.hasForSuperState(this) || this.hasForSuperState(s)) {
            return false;
        }
        AbstractState commonSuper = this.commonSuperWith(s);
        if (commonSuper == null) {
            return false;
        }
        if (commonSuper._xor == false) {
            return true;
        }
//        AbstractState brother = this.brother();
//        for (AbstractState state = s._node; state != commonSuper; state = state._node) {
//            if (state == brother && state._xor == false && Pseudo_state.equals(brother.name())) {
//                return true;
//            }
//        }
//        brother = s.brother();
//        for (AbstractState state = this._node; state != commonSuper; state = state._node) {
//            if (state == brother && state._xor == false && Pseudo_state.equals(brother.name())) {
//                return true;
//            }
//        }
        return false;
    }

    /**
     * This method indicates if two states are exclusive.
     *
     * @param s The state for which exclusion is established or not.
     * @return
     */
    public boolean isXorWith(AbstractState s) {
        return !isAndWith(s);
    }

    /**
     * This method amounts to calling
     * <CODE> allowedEvent(event,object,action,null);</CODE>.
     */
    public void allowedEvent(String event, Object object, String action) {
        allowedEvent(event, object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public void allowedEvent(String event, Object object, String action, Object[] args) {
        allowedEvent(event, object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,null,"true",object,action,args,reentrance_mode);</CODE>.
     */
    public void allowedEvent(String event, Object object, String action, Object[] args, byte reentrance_mode) {
        allowedEvent(event, null, "true", object, action, args, reentrance_mode);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,null,object,action,null,AbstractState.No_reentrance);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object object, String action) {
        allowedEvent(event, guard_object, guard_action, null, object, action, null, AbstractState.No_reentrance);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,null,object,action,null,reentrance_mode);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object object, String action, byte reentrance_mode) {
        allowedEvent(event, guard_object, guard_action, null, object, action, null, reentrance_mode);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,null,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object object, String action, Object[] args) {
        allowedEvent(event, guard_object, guard_action, null, object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,null,object,action,args,reentrance_mode);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object object, String action, Object[] args, byte reentrance_mode) {
        allowedEvent(event, guard_object, guard_action, null, object, action, args, reentrance_mode);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,guard_args,object,action,null);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object[] guard_args, Object object, String action) {
        allowedEvent(event, guard_object, guard_action, guard_args, object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,guard_args,object,action,null,reentrance_mode);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, byte reentrance_mode) {
        allowedEvent(event, guard_object, guard_action, guard_args, object, action, null, reentrance_mode);
    }

    /**
     * This method amounts to calling
     * <CODE>allowedEvent(event,guard_object,guard_action,guard_args,object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, Object[] args) {
        allowedEvent(event, guard_object, guard_action, guard_args, object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method registers the allowed events for a given state.
     */
    public void allowedEvent(String event, Object guard_object, String guard_action, Object[] guard_args, Object object, String action, Object[] args, byte reentrance_mode) {
        if (event == null) {
            return;
        }
        if (event.equals(Pseudo_event)) {
            return;
        }
        AbstractGuard guard = guard(guard_object, guard_action, guard_args);
        java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> guards = _allowed_events.get(event);
        java.util.Vector<AbstractAction> actions;
        if (guards == null) {
            guards = new java.util.HashMap<>();
            actions = new java.util.Vector<>();
            guards.put(guard, actions);
            _allowed_events.put(event, guards);
        } else {
            actions = guards.remove(guard);
            if (actions == null) {
                actions = new java.util.Vector<>();
            }
            guards.put(guard, actions);
        }
        if (object != null && action != null) {
            AbstractAction new_action = action(object, action, args, reentrance_mode);
            // The following statements rely on a specific implementation of the 'public boolean equals(Object action)' method in the 'AbstractAction' class:
            int index = actions.indexOf(new_action); // Search of first index is safe because duplicates have not been allowed
            if (index != -1) {
                actions.setElementAt(new_action, index); // Arguments' update, e.g., 'search' event in 'Stack' component
            } else {
                actions.addElement(new_action);
            }
        }
    }

    /**
     * This method executes the actions of allowed events, which are eligible
     * during a run-to-completion cycle; For internal use only.
     */
    protected void allowedEvent(String event, java.util.Map<Transition, Object[]> execution, java.util.Vector<Transition> eliminated_transitions, StringBuffer verbose) {
        if (_left != null) {
            _left.allowedEvent(event, execution, eliminated_transitions, verbose);
        }
        if (_right != null) {
            _right.allowedEvent(event, execution, eliminated_transitions, verbose);
        }
        java.util.Map<AbstractGuard, java.util.Vector<AbstractAction>> guards = _allowed_events.get(event);
        if (guards != null) {
            if (_active) {
                for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> entry : guards.entrySet()) {
                    AbstractGuard guard = entry.getKey();
                    try {
                        if (guard.execute()) {
                            boolean execute = true;
                            for (java.util.Iterator<Transition> i = execution.keySet().iterator(); i.hasNext();) {
                                Transition transition = i.next();
                                AbstractState from = transition._from;
                                AbstractState to = transition._to;
                                if (this == from) {
                                    verbose.append("\t\t{WARNING}overridden by allowed event: " + from._name + " -> " + to._name + ", guard: [true]\n");
                                    if (!eliminated_transitions.contains(transition)) {
                                        eliminated_transitions.addElement(transition);
                                    }
                                }
                                if (from.hasForSuperState(this)) {
                                    execute = false;
                                }
                            }
                            if (execute) {
                                java.util.Vector<AbstractAction> executed_actions = guards.get(guard);
                                for (int i = 0; i < executed_actions.size(); i++) {
                                    AbstractAction executed_action = executed_actions.elementAt(i);
                                    try {
                                        executed_action.execute();
                                    } catch (State_exception se) {
                                    } finally {
                                        verbose.append(Allowed_event_display_message + event + "/" + executed_action.verbose() + "\n");
                                    }
                                }
                            }
                        }
                    } catch (State_exception se) {
                        verbose.append("\t{WARNING}canceled (allowed event): " + _name + ", guard: [" + guard.verbose() + "]\n");
                    }
                }
            }
        }
    }

    /**
     * This method returns the <I>allow:</I> actions of the state.
     *
     * @return The <I>allow:</I> actions (if any) in a printable form
     */
    public String get_allowed_events_label() {
        if (_allowed_events.isEmpty()) {
            return null;
        }
        String allowed_events_label = "";
        for (java.util.Iterator<String> i = _allowed_events.keySet().iterator(); i.hasNext();) {
            String event = i.next();
            for (java.util.Map.Entry<AbstractGuard, java.util.Vector<AbstractAction>> entry : _allowed_events.get(event).entrySet()) {
                AbstractGuard guard = entry.getKey();
                java.util.Vector<AbstractAction> actions = _allowed_events.get(event).get(guard);
                for (int j = 0; j < actions.size(); j++) {
                    AbstractAction action = actions.elementAt(j);
                    allowed_events_label += Allow_header_text + event + guard.to_UML() + '/' + action.to_UML() + '\n';
                }
            }
        }
        return allowed_events_label;
    }

    private enum _History {
        DEEP,
        NONE,
        SHALLOW
    }
    /**
     * This variable represents the fact that a state is or not a history state.
     *
     * @see AbstractState#shallow_history()
     * @see AbstractState#deep_history()
     */
    private _History _history_type = _History.NONE;
    /**
     * This variable records the history of a state by pointing to its last (if
     * any) visited substate. Possible values are between <CODE>_left</CODE> and
     * <CODE>_right</CODE>.
     *
     * @see AbstractState#deep_history()
     * @see AbstractState#shallow_history()
     */
    private AbstractState _history = null;

    /**
     * This method amounts to configuring a state as a (shallow) history state:
     * <I>H</I> notation. History setup <b>is only possible after full state
     * machine structuring</b>.
     */
    public void shallow_history() {
        if (_history_type == _History.NONE) {
            _history_type = _History.SHALLOW;
        }
    }

    /**
     * This method amounts to configuring a state as a (deep) history state:
     * <I>H*</I> notation. History setup <b>is only possible after full state
     * machine structuring</b>.
     */
    public void deep_history() {
        if (_history_type == _History.NONE) {
            _history_type = _History.DEEP;
        }
        if (_left != null) {
            _left.deep_history();
        }
        if (_right != null) {
            _right.deep_history();
        }
    }

    /**
     * This method tests if a state is a (shallow) history state.
     */
    public boolean is_shallow_history() {
        return _history_type == _History.SHALLOW && !leaf();
    }

    /**
     * This method tests if a state is a (deep) history state.
     */
    public boolean is_deep_history() {
        boolean ascendant_is_deep = false;
        for (AbstractState node = _node; node != null; node = node._node) {
            if (node._history_type == _History.DEEP) {
                ascendant_is_deep = true;
                break;
            }
        }
        return _history_type == _History.DEEP && !leaf() && !ascendant_is_deep;
    }

    /**
     * This method amounts to calling
     * <CODE>doActivity(object,activity,null);</CODE>.
     */
    public AbstractState doActivity(Object object, String activity) {
        return doActivity(object, activity, null);
    }

    /**
     * This method associates an activity <I>a</I> with a state according to the
     * following UML formalism: <I>do/ a</I>. Only one activity may be setup for
     * the same state. Normally, activities are not intended to communicate with
     * other software components. It is thus preferable to encapsulate local
     * data transformations in activities. Such transformations are not supposed
     * to require any control, synchronization, etc.
     *
     * @return The state itself
     */
    public AbstractState doActivity(Object object, String activity, Object[] args) {
        _do = activity(object, activity, args);
        return this;
    }

    /**
     * This method returns the <I>do/ </I>activity of the state.
     *
     * @return The <I>do/ </I> activity (if any) in a printable form.
     */
    public String get_do_label() {
        // 'SendSignalAction_symbol' is removed as first character for 'do' activities:
        return _do == null ? null : Do_header_text + _do.to_UML().substring(1);
    }

    /**
     * This method amounts to calling
     * <CODE>set_entryAction(object,action,null);</CODE>.
     */
    public AbstractState set_entryAction(Object object, String action) {
        return set_entryAction(object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>set_entryAction(object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public AbstractState set_entryAction(Object object, String action, Object[] args) {
        return set_entryAction(object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method associates an action <I>a</I> with a state according to the
     * following UML formalism: <I>entry/ a</I>. Multiple actions for the same
     * state require multiple uses of this method.
     *
     * @param reentrance_mode The {@link AbstractState#Reentrance} class
     * variable may be used as a value for this parameter; This value prevents
     * unanticipated reentrance
     * @return The state itself
     */
    public AbstractState set_entryAction(Object object, String action, Object[] args, byte reentrance_mode) {
        AbstractAction abstract_action = action(object, action, args, reentrance_mode);
        _entry.addElement(abstract_action);
        return this;
    }

    /**
     * This method amounts to calling
     * <CODE>reset_entryAction(object,action,null);</CODE>.
     */
    public AbstractState reset_entryAction(Object object, String action) {
        return reset_entryAction(object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>reset_entryAction(object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public AbstractState reset_entryAction(Object object, String action, Object[] args) {
        return reset_entryAction(object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method associates an action <I>a</I> with a state according to the
     * following UML formalism: <I>entry/ a</I>. This method aims at erasing an
     * already recorded entry action due to the change of the
     * <CODE>object</CODE> and/or <CODE>args</CODE> parameters.
     *
     * @param reentrance_mode The {@link AbstractState#Reentrance} class
     * variable may be used as a value for this parameter; This value prevents
     * unanticipated reentrance
     * @return The state itself
     */
    public AbstractState reset_entryAction(Object object, String action, Object[] args, byte reentrance_mode) {
        int i = 0;
        while (i < _entry.size()) {
            AbstractAction a = _entry.elementAt(i);
            if (a._action.equals(action) && a.compare_args(args)) {
                break;
            }
            i++;
        }
        if (i == _entry.size()) {
            set_entryAction(object, action, args, reentrance_mode);
        } else {
            _entry.setElementAt(action(object, action, args, reentrance_mode), i);
        }
        return this;
    }

    /**
     * This method returns the <I>entry/ </I>actions of the state.
     *
     * @return The <I>entry/ </I>actions (if any) in a printable form.
     */
    private String get_entries_label() {
        if (_entry.isEmpty()) {
            return null;
        }
        String entries = "";
        for (int i = 0; i < _entry.size(); i++) {
            entries += Entry_header_text + _entry.elementAt(i).to_UML() + '\n';
        }
        return entries;
    }

    /**
     * This method amounts to calling
     * <CODE>set_exitAction(object,action,null);</CODE>.
     */
    public AbstractState set_exitAction(Object object, String action) {
        return set_exitAction(object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>set_exitAction(object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public AbstractState set_exitAction(Object object, String action, Object[] args) {
        return set_exitAction(object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method associates an action <I>a</I> with a state according to the
     * following UML formalism: <I>exit/ a</I>. Multiple actions for the same
     * state require multiple uses of this method.
     *
     * @param reentrance_mode The {@link AbstractState#Reentrance} class
     * variable may be used as a value for this parameter; This value prevents
     * unanticipated reentrance
     * @return The state itself
     */
    public AbstractState set_exitAction(Object object, String action, Object[] args, byte reentrance_mode) {
        AbstractAction abstract_action = action(object, action, args, reentrance_mode);
        _exit.addElement(abstract_action);
        return this;
    }

    /**
     * This method amounts to calling
     * <CODE>reset_exitAction(object,action,null);</CODE>.
     */
    public AbstractState reset_exitAction(Object object, String action) {
        return reset_exitAction(object, action, null);
    }

    /**
     * This method amounts to calling
     * <CODE>reset_exitAction(object,action,args,AbstractState.No_reentrance);</CODE>.
     */
    public AbstractState reset_exitAction(Object object, String action, Object[] args) {
        return reset_exitAction(object, action, args, AbstractState.No_reentrance);
    }

    /**
     * This method associates an action <I>a</I> with a state according to the
     * following UML formalism: <I>exit/ a</I>. This method aims at erasing an
     * already recorded exit action due to the change of the <CODE>object</CODE>
     * and/or <CODE>args</CODE> parameters.
     *
     * @param reentrance_mode The {@link AbstractState#Reentrance} class
     * variable may be used as a value for this parameter; This value prevents
     * unanticipated reentrance
     * @return The state itself
     */
    public AbstractState reset_exitAction(Object object, String action, Object[] args, byte reentrance_mode) {
        int i = 0;
        while (i < _exit.size()) {
            AbstractAction a = _exit.elementAt(i);
            if (a._action.equals(action) && a.compare_args(args)) {
                break;
            }
            i++;
        }
        if (i == _exit.size()) {
            set_exitAction(object, action, args, reentrance_mode);
        } else {
            _exit.setElementAt(action(object, action, args, reentrance_mode), i);
        }
        return this;
    }

    /**
     * This method returns the <I>exit/ </I>actions of the state.
     *
     * @return The <I>exit/ </I>actions (if any) in a printable form.
     */
    private String get_exits_label() {
        if (_exit.isEmpty()) {
            return null;
        }
        String exits = "";
        for (int i = 0; i < _exit.size(); i++) {
            exits += Exit_header_text + _exit.elementAt(i).to_UML() + '\n';
        }
        return exits;
    }

    /**
     * This method amounts to calling
     * <CODE>stateInvariant(object,invariant,null);</CODE>.
     */
    public AbstractState stateInvariant(Object object, String invariant) {
        return stateInvariant(object, invariant, null);
    }

    /**
     * This method associates an invariant with a state as proposed by the UML
     * formalism. Only one invariant may be setup for the same state. An
     * invariant is evaluated during a run-to-completion cycle by means of a
     * (dedicated) executed action.
     *
     * @param object The object in charge of executing the action, corresponding
     * to the invariant to be evaluated
     * @param invariant The action to be executed, corresponding to the
     * invariant to be evaluated
     * @param args The action's arguments, if any
     * @return The state itself
     * @see
     * com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)
     */
    public AbstractState stateInvariant(Object object, String invariant, Object[] args) {
        _invariant = action(object, invariant, args, No_reentrance);
        return this;
    }

    /**
     * Internal use only; This method computes the invariant of a state, this
     * state being active or not at call time.
     *
     * @return The result of evaluating the state's invariant by calling the
     * Java method, if any, attached to this state as invariant evaluator This
     * methods returns <CODE>true</CODE> if there is no attached evaluation
     * method or the latter does not return a Boolean type and no exception
     * occurs when calling it In contrast, this methods raises an exception if
     * the state still lasts (its attached activity is not terminated) or an
     * exception occurs when evaluating the invariant
     * @see AbstractState#stateInvariant(Object,String,Object[])
     * @see AbstractState#deepStateInvariant()
     */
    protected String shallowStateInvariant() throws State_exception {
        if (_do != null) {
            _do.wait_for_completion();
        }
        if (Pseudo_state.equals(_name) || _invariant == null) {
            return "";
        }
        try {
            _invariant.execute();
            return get_invariant_label() + Textual_view_subject_separator + _invariant.printable_result();
        } catch (State_exception se) {
            throw new State_exception("Invariant cannot be evaluated since invariant evaluator failed: " + _invariant.verbose());
        }
    }

    /**
     * Internal use only; This method computes the global invariant of a state
     * machine. This invariant is equal to a logical <I>and</I> for all of the
     * invariants of its <b>active</b> states.
     *
     * @see AbstractState#stateInvariant(Object,String,Object[])
     * @see AbstractState#shallowStateInvariant()
     * @see
     * com.pauware.pauware_engine.Core.AbstractStateMachine#run_to_completion(String,boolean)
     */
    protected String deepStateInvariant() throws State_exception {
        if (_active) {
            String result = shallowStateInvariant();
            result = result.equals("") ? "" : "\n\t\t" + result;
            if (_left != null && _right != null) {
                result += _left.deepStateInvariant() + _right.deepStateInvariant();;
            }
            return result;
        }
        return "";
    }

    /**
     * This method returns the <I>invariant: </I>action of the state.
     *
     * @return The <I>invariant: </I>action (if any) in a printable form.
     */
    public String get_invariant_label() {
        return _invariant == null ? null : Invariant_header_text + '[' + _invariant.to_UML() + ']';
    }

    /**
     * Internal use only; This method is called at state machine starting time.
     * It is also called within any run-to-completion cycle. In this latter
     * case, this method is called by
     * {@link AbstractStateMachine#activate(AbstractState,AbstractState,StringBuffer)}.
     */
    protected void entry(java.util.Stack<AbstractState> state_execution_flow, StringBuffer verbose) throws State_exception {
        // Recursive method, first call: 'state_execution_flow' is a 'Stack' object such that its bottom is equal to '_to', the state to be reached in a transition from '_from' to '_to'
        if ((_left == null && _right != null) || (_left != null && _right == null)) {
            throw new State_based_exception("Incorrect state entry configuration (unexpected 'null')", _left, _right);
        }
        if (_active == false) { // When already active, this stops recursion
            try {
                for (int i = 0; i < _entry.size(); i++) {
                    AbstractAction abstractAction = _entry.elementAt(i);
                    abstractAction.execute();
                    verbose.append(Entry_action_display_message + abstractAction.verbose() + "\n");
                }
            } finally {
                if (_left != null && _right != null) { // Leaf state, stop recursion
                    if (_left.isAndWith(_right)) {
                        AbstractState s = null;
                        if (state_execution_flow != null) {
                            if (state_execution_flow.empty()) {
                                _left.entry(null, verbose);
                                _right.entry(null, verbose);
                            } else {
                                s = state_execution_flow.pop();
                                if (_left == s) {
                                    _left.entry(state_execution_flow, verbose);
                                    _right.entry(null, verbose);
                                } else {
                                    if (_right == s) {
                                        _left.entry(null, verbose);
                                        _right.entry(state_execution_flow, verbose);
                                    } else {
                                        throw new State_based_exception("Incorrect state execution flow (neither '_left' nor '_right' is on the flow)", _left, _right);
                                    }
                                }
                            }
                        } else {
                            _left.entry(null, verbose);
                            _right.entry(null, verbose);
                        }
                    } else {
                        if (_left.isXorWith(_right)) {
                            if (state_execution_flow != null) { // 'entry(state_execution_flow)' has been called within the context of a state execution flow, input and history states are bypassed
                                AbstractState s = null;
                                if (state_execution_flow.empty()) {
                                    if (_left.isInputState()) {
                                        if (_right.isInputState()) {
                                            throw new State_based_exception("Incorrect input state configuration (two contradicting default input states)", _left, _right);
                                        } else {
                                            // '_left' is input state while '_right' is not
                                            /**
                                             * History issues (Dec. 2012)
                                             */
                                            if (_history_type == _History.NONE) {
                                                _left.entry(null, verbose);
                                            } else {
                                                // 'this' is a history state:
                                                if (_history == null) {
                                                    // first time, there is no recorded history
                                                    _left.entry(null, verbose);
                                                } else {
                                                    _history.entry(null, verbose);
                                                }
                                            }
                                            /**
                                             * End of history issues (Dec. 2012)
                                             */
                                        }
                                    }
                                    if (_right.isInputState()) {
                                        if (_left.isInputState()) {
                                            throw new State_based_exception("Incorrect input state configuration (two contradicting default input states)", _left, _right);
                                        } else {
                                            // '_right' is input state while '_left' is not
                                            /**
                                             * History issues (Dec. 2012)
                                             */
                                            if (_history_type == _History.NONE) {
                                                _right.entry(null, verbose);
                                            } else {
                                                // 'this' is a history state:
                                                if (_history == null) {
                                                    // first time, there is no recorded history
                                                    _right.entry(null, verbose);
                                                } else {
                                                    _history.entry(null, verbose);
                                                }
                                            }
                                            /**
                                             * End of history issues (Dec. 2012)
                                             */
                                        }
                                    }
                                } else {
                                    s = state_execution_flow.pop();
                                    if (_left == s) {
                                        _left.entry(state_execution_flow, verbose);
                                    } else {
                                        if (_right == s) {
                                            _right.entry(state_execution_flow, verbose);
                                        } else {
                                            throw new State_based_exception("Incorrect state execution flow (neither '_left' nor '_right' is on the flow)", _left, _right);
                                        }
                                    }
                                }
                            } else { // 'entry(state_execution_flow)' has NOT been called within the context of a state execution flow, input and history states are handled
                                if (_left.isInputState()) {
                                    if (_right.isInputState()) {
                                        throw new State_based_exception("Incorrect input state configuration (two contradicting default input states)", _left, _right);
                                    } else {
                                        // '_left' is input state while '_right' is not
                                        /**
                                         * History issues (Dec. 2012)
                                         */
                                        if (_history_type == _History.NONE) {
                                            _left.entry(null, verbose);
                                        } else {
                                            // 'this' is a history state:
                                            if (_history == null) {
                                                // first time, there is no recorded history
                                                _left.entry(null, verbose);
                                            } else {
                                                _history.entry(null, verbose);
                                            }
                                        }
                                        /**
                                         * End of history issues (Dec. 2012)
                                         */
                                    }
                                }
                                if (_right.isInputState()) {
                                    if (_left.isInputState()) {
                                        throw new State_based_exception("Incorrect input state configuration (two contradicting default input states)", _left, _right);
                                    } else {
                                        // '_right' is input state while '_left' is not
                                        /**
                                         * History issues (Dec. 2012)
                                         */
                                        if (_history_type == _History.NONE) {
                                            _right.entry(null, verbose);
                                        } else {
                                            // 'this' is a history state:
                                            if (_history == null) {
                                                // first time, there is no recorded history
                                                _right.entry(null, verbose);
                                            } else {
                                                _history.entry(null, verbose);
                                            }
                                        }
                                        /**
                                         * End of history issues (Dec. 2012)
                                         */
                                    }
                                }
                            }

                        } else {
                            throw new State_based_exception("Incorrect state entry configuration (neither 'and' nor 'xor'", _left, _right);
                        }
                    }
                }
                try {
                    if (_do != null) {
                        _do.execute();
                        verbose.append(Do_activity_display_message + _do.verbose().substring(1) + "\n");
                    }
                } finally {
                    _active = true;
                    if (!Pseudo_state.equals(_name)) {
                        verbose.append(Activated_state_display_message + _name + "\n");
                    }
                }
            }
        }
    }

    /**
     * Internal use only; This method is called at state machine stopping time
     * for any run-to-completion cycle. This method is called by
     * {@link AbstractStateMachine#disactivate(AbstractState,AbstractState,StringBuffer)}.
     */
    protected boolean exit(StringBuffer verbose) throws State_exception {
        if ((_left == null && _right != null) || (_left != null && _right == null)) {
            throw new State_based_exception("Incorrect state exit configuration (unexpected 'null')", _left, _right);
        }
        if (_active == true) {
            /**
             * The activity, if any, attached to the state must be fully
             * completed
             */
            if (_do != null) {
                _do.wait_for_completion();
            }
            if (_left != null && _right != null) {
                /**
                 * History issues (Dec. 2012)
                 */
                boolean left_exit = _left.exit(verbose);
                boolean right_exit = _right.exit(verbose);
                if (left_exit && !right_exit) {
                    _history = _left;
                }
                if (right_exit && !left_exit) {
                    _history = _right;
                }
                /**
                 * End of history issues (Dec. 2012)
                 */
            }
            try {
                // while (isLasting()) {/* semantic variation point: this may lead to a;d;((e;f)||b);c instead of a;d;(b||e);f;c */}
                for (int i = 0; i < _exit.size(); i++) {
                    AbstractAction abstractAction = _exit.elementAt(i);
                    abstractAction.execute();
                    verbose.append(Exit_action_display_message + abstractAction.verbose() + "\n");
                }
            } finally {
                _active = false;
                if (!Pseudo_state.equals(_name)) {
                    verbose.append(Disactivated_state_display_message + _name + "\n");
                }
            }
            // Dec. 2012. One has really exited from 'this':
            return true;
        } else {
            // Dec. 2012. One has not exited from 'this' because 'this' was inactive when calling 'exit':
            return false;
        }
    }

    /**
     * This method forces a state to be an input state. The UML notation is a
     * black point with an arrow pointing to the state. At state machine
     * creation time, some states of a state machine must be declared as input
     * states in order to guarantee the state machine's determinism.
     *
     * @see
     * AbstractStateMachine#AbstractStateMachine(AbstractState,String,boolean)
     */
    public void inputState() {
        if (isOutputState()) {
            return;
        }
        _inputState = true;
    }

    /**
     * This method tests if a state is an input state.
     */
    public boolean isInputState() {
        return _inputState;
    }

    /**
     * This method enables the naming of a state.<b>Caution</b>: there is
     * <b>no</b> naming checking in <I>PauWare</I>.For instance, naming two
     * states with the same name would probably lead to unpredictable behaviors,
     * even defects.
     *
     * @param name The given name.
     * @return The state for which the name field has just been setup.
     */
    public AbstractState name(String name) {
        if (name != null) {
            _name = name;
        }
        return this;
    }

    /**
     * This method forces a state to be an output state. The UML notation is a
     * black point surrounded by a circle which is pointed at by an arrow coming
     * out of another state. This method has no effect for the moment; It will
     * be used in a forthcoming API.
     */
    public void outputState() {
        if (isInputState()) {
            return;
        }
        _outputState = true;
    }

    /**
     * Completion transitions (i.e., those without any event label) are
     * automatically triggered when reaching output states.
     *
     * @since ver. 1.3
     */
    protected void set_completion(AbstractState s) {
        if (_outputState) {
            set_entryAction(s, Completion, null, Reentrance);
        }
        if (_left != null) {
            _left.set_completion(s);
        }
        if (_right != null) {
            _right.set_completion(s);
        }
    }

    /**
     * This method tests if a state is an output state.
     */
    public boolean isOutputState() {
        return _outputState;
    }

    /**
     * This method answers the question: is this state a fictitious state
     * (<I>i.e.</I>, both leaf and pseudo-state) of the state machine?
     */
    public boolean fictitious() {
        return leaf() && Pseudo_state.equals(_name);
    }

    /**
     * This method answers the question: is this state a leaf state
     * (<I>i.e.</I>, without direct or indirect substates) of the state machine?
     */
    public boolean leaf() {
        return _left == null && _right == null;
    }

    /**
     * This method answers the question: is this state the most outer state of
     * the state machine? The answer must always be <CODE>false</CODE> for an
     * instance of the <CODE>AbstractState</CODE> type.
     */
    public boolean root() /*throws State_based_exception*/ {
//        if (_node == this) {
//            throw new State_based_exception("Root error:", _node, this);
//        }
        return false;
    }

    /**
     * Management facility: this method is a utility for runtime checking. It
     * verifies if an active state, which has its "brother" state <b>active</b>,
     * shares a orthogonality relationship with it. It also verifies if an
     * active state, which has its "brother" state <b>inactive</b>, shares an
     * exclusion relationship with it.
     */
    public void verify() throws State_based_exception {
        if (isXorWith(brother()) && _active && brother()._active) {
            throw new State_based_exception("Xor error: ", this, brother());
        }
        if (isAndWith(brother()) && _active && !brother()._active) {
            throw new State_based_exception("And error: ", this, brother());
        }
    }

    /**
     * Management facility: this method is offered by a software component,
     * which implements the <CODE>Manageable_base</CODE> interface.
     *
     * @see com.pauware.pauware_engine.Core.Manageable_base
     */
    protected final static char _Left_parenthesis = '(';
    protected final static char _Right_parenthesis = ')';
    protected final static String _AND = ".AND.";

    public String current_state() {
        String name = "";
        if (_active) {
            if (leaf()) {
                name = _name;
            } else {
                String left_name = _left.current_state();
                String right_name = _right.current_state();
                if (_left.isAndWith(_right)) {
                    if (!Pseudo_state.equals(left_name)) {
                        name = _Left_parenthesis + left_name;
                        if (!Pseudo_state.equals(right_name)) {
                            name += _AND + right_name + _Right_parenthesis;
                        } else {
                            name += _Right_parenthesis;
                        }
                    } else {
                        if (!Pseudo_state.equals(right_name)) {
                            name += _Left_parenthesis + right_name + _Right_parenthesis;
                        }
                    }
                } else {
                    if (_left._active) {
                        name = _Left_parenthesis + left_name + _Right_parenthesis;
                    } else {
                        name = _Left_parenthesis + right_name + _Right_parenthesis;
                    }
                }
                if (Pseudo_state.equals(_name)) {
                    name = _name + name;
                }
            }
        }
        return name;
    }

    /**
     * Management facility: this method is offered by a software component,
     * which implements the <CODE>Manageable_base</CODE> interface.
     *
     * @see com.pauware.pauware_engine.Core.Manageable_base
     */
//    Moved to AbstractStateMachine instead:
//    public boolean in_state(String name) {
//        return current_state().indexOf("(" + name) != -1;
//    }
    /**
     * This method returns the name of a state.
     *
     * @return The returned value can be the value of the
     * {@link AbstractState#Pseudo_state} class variable; One has however to
     * notice that pseudo-states do not require direct manipulation
     */
    public String name() {
        // The '_name' field is by construction never equal to 'null'
        return _name;
    }

    /**
     * Internal use only; This method is called by
     * {@link AbstractStateMachine#to_state(String)}.
     *
     * @return The state to be reached, if any
     */
    protected AbstractState lookup(String name) throws State_exception {
        // The '_name' field is by construction never equal to 'null'
        if (_name.equals(name)) {
            return this;
        } else {
            if (leaf()) {
                return null;
            } else {
                AbstractState to = _left.lookup(name);
                if (to == null) {
                    return _right.lookup(name);
                } else {
                    return to;
                }
            }
        }
    }
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Allow_header_text = "allow: ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Exit_header_text = "exit/ ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Entry_header_text = "entry/ ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Do_header_text = "do/ ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Invariant_header_text = "invariant: ";

    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Allowed_event_display_message = "\tALLOWED EVENT: ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Exit_action_display_message = "\tEXIT ACTION: ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Disactivated_state_display_message = "\tDISACTIVATED STATE: ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Entry_action_display_message = "\tENTRY ACTION: ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Do_activity_display_message = "\tDO ACTIVITY: ";
    /**
     * For trace and visualization purposes: this class variable is used by the
     * verbose mode. It is also used by
     * <I>PauWare2Web</I> tool to control what is displayed on the HTML page.
     *
     * @since ver. 1.3.
     */
    public static final String Activated_state_display_message = "\tACTIVATED STATE: ";
    /**
     * For visualization purposes only: this class variable is used by the
     * <I>PauWare2Web</I> tool to replace unacceptable characters.
     *
     * @since ver. 1.3
     */
    public static final char Neutral_character = '_';
    /**
     * For visualization purposes only: this class variable is used for breaking
     * into pieces textual views, which are later processed by the
     * <I>PauWare2Web</I> tool.
     *
     * @since ver. 1.3
     */
    public static final String Textual_view_subject_separator = "@";

    /**
     * For visualization purposes only: this class method is used by the
     * <I>PauWare2Web</I> tool to clean up unacceptable characters.
     *
     * @param name The name of an event or a state in a normalized format,
     * <i>i.e.</i>, no space, etc.
     * @return The cleaned name
     * @since ver. 1.3
     */
    public static String Clean_up(String name) {
        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 'a' && name.charAt(i) <= 'z') || (name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') || (name.charAt(i) >= '0' && name.charAt(i) <= '9'))) {
                name = name.replace(name.charAt(i), Neutral_character);
            }
        }
        return name;
        /**
         * Java SE version
         */
//        return name.replaceAll("[^a-zA-Z0-9]", String.valueOf(Neutral_character));
        /**
         * End of Java SE version
         */
    }

    /**
     * For visualization purposes only, private use
     *
     * @since ver. 2.0
     */
    protected enum _Cross_over {
        ASCENDING, /* from - > to while from is nested in to */
        DESCENDING, /* from - > to while to is nested in from */
        NONE,
        SPAN,
        WEIRD // 'Y --> S1' in 'Guard_conflict.PauWare2Web'
    }

    /**
     * For visualization purposes only, private use
     *
     * @param from The source state of the transition while <code>this</code>
     * plays the role of the transition's target state
     * @return A value of the <code>_Cross_over</code> enumerated type to
     * establish how to draw the transition in the context of PlantUML strong
     * limitations about orthogonality
     * @since ver. 2.0
     */
    protected _Cross_over _cross_over(AbstractState from) {
        if (from == this) { // Self-transition...
            return _Cross_over.NONE;
        }
        AbstractState commonSuper = from.commonSuperWith(this); // 'this' plays the role of 'to'
        boolean no_cross_over = this.brother().fictitious() ? true : this._node._xor; // 'Pong' as single substate of 'Player'...
        if (commonSuper == from) {
            for (AbstractState state = this._node; no_cross_over && state != commonSuper; state = state._node) {
                no_cross_over &= (state == this._node && this.brother().fictitious()) ? true : state._xor; // Belonging to orthogonal region switches to 'false'...
            }
            no_cross_over &= from._xor;
            if (no_cross_over == false) {
                return _Cross_over.DESCENDING;
            } else {
                if (this.brother()._xor == false && Pseudo_state.equals(this.brother().name())) {
                    return _Cross_over.WEIRD; // 'Guard_conflict.PauWare2Web': 'Y --> S1'
                }
                return _Cross_over.NONE;
            }
        } else {
            if (commonSuper == this) {
                for (AbstractState state = from._node; no_cross_over && state != commonSuper; state = state._node) {
                    no_cross_over &= (state == from._node && from.brother().fictitious()) ? true : state._xor;
                }
                no_cross_over &= this._xor;
                return no_cross_over ? _Cross_over.NONE : _Cross_over.ASCENDING;
            } else { // 'S21' -> 'Idle' ('commonSuper' == 'My_device')
                for (AbstractState state = from._node; no_cross_over && state != commonSuper; state = state._node) {
                    no_cross_over &= (state == from._node && from.brother().fictitious()) ? true : state._xor;
                }
                no_cross_over &= commonSuper._xor;
                if (!no_cross_over) {
                    return _Cross_over.SPAN;
                } else {
                    for (AbstractState state = this._node; no_cross_over && state != commonSuper; state = state._node) {
                        no_cross_over &= (state == this._node && this.brother().fictitious()) ? true : state._xor;
                    }
                    no_cross_over &= commonSuper._xor;
                    if (!no_cross_over) {
                        return _Cross_over.SPAN;
                    } else {
                        return _Cross_over.NONE;
                    }
                }
            }
        }
    }

    /**
     * For visualization purposes only: this recursive method is internally
     * called by the {@link AbstractStateMachine#to_PlantUML()} method to
     * compute the state views of the state machine.
     *
     * @param transition_views The transitions in the PlantUML form.
     * @return The PlantUML string from which the graph of states and
     * transitions is computed.
     * @since ver. 1.3
     */
    protected String _compute_state_views(java.util.Map<String, java.util.Set<String>> transition_views) {
        String state_name = Clean_up(_name);
        String left_name = _left != null ? Clean_up(_left._name) : "";
        String right_name = _right != null ? Clean_up(_right._name) : "";
        String state_view = "";
        String history_view = "";
        String input_state_view = "";
        String output_state_view = "";
        if (!Pseudo_state.equals(_name)) {
            if (is_shallow_history()) {
                history_view = "note left of " + state_name + " : " + "H" + "\n";
            } else {
                if (is_deep_history()) {
                    history_view = "note left of " + state_name + " : " + "H*" + "\n";
                }
            }
            if (isInputState()) {
//                assert (!isOutputState());
                input_state_view += "[*]" + _PlantUML_transition + state_name + "\n";
            }
            if (isOutputState()) {
//                assert (!isInputState());
                output_state_view += state_name + _PlantUML_transition + "[*]\n";
            }
            String allowed_events_label = get_allowed_events_label();
            if (allowed_events_label != null && !allowed_events_label.equals("")) {
                String[] allowed_events_labels = allowed_events_label.split("\n");
                for (String label : allowed_events_labels) {
                    state_view += state_name + " : " + label + "\n";
                }
            }
            String invariant = get_invariant_label();
            if (invariant != null) {
                state_view += state_name + " : " + invariant + "\n";
            }
            String entries = get_entries_label();
            if (entries != null && !entries.equals("")) {
                String[] entries_labels = entries.split("\n");
                for (String label : entries_labels) {
                    state_view += state_name + " : " + label + "\n";
                }
            }
            String do_activity = get_do_label();
            if (do_activity != null) {
                state_view += state_name + " : " + do_activity + "\n";
            }
            String exits = get_exits_label();
            if (exits != null && !exits.equals("")) {
                String[] exits_labels = exits.split("\n");
                for (String label : exits_labels) {
                    state_view += state_name + " : " + label + "\n";
                }
            }
        }
        if (leaf()) {
            if (!Pseudo_state.equals(_name)) { // Test obligatoire: voir 'Single_nesting'
                state_view = history_view + input_state_view + output_state_view + _PlantUML_state + state_name + " {\n" + state_view;
                for (String transition : java.util.Optional.ofNullable(transition_views.remove(state_name)).orElse(java.util.Collections.emptySet())) {
                    state_view += transition;
                }
                state_view += "}\n";
            }
        } else { // Composite state...
            String left = _left._compute_state_views(transition_views);
            String right = _right._compute_state_views(transition_views);
            if (Pseudo_state.equals(_name)) {
                if (_left.isXorWith(_right)) {
// (PlantUML limitations) - composite states having inner *ORTHOGONAL* regions impose the creation of surrouding states:
                    if (this.isAndWith(this.brother()) && (!left.startsWith(_PlantUML_state) || !right.startsWith(_PlantUML_state))) {
                        /**
                         * L'état 'Operate' est composé de régions orthogonales.
                         * 'Ambient_temperature_displaying_Current_date_and_time_displaying'
                         * typiquement doit être créé pour exprimer par exemple
                         * que 'Current_date_and_time_displaying' est un état
                         * d'entrée. Au-delà, il faut "descendre" les
                         * transitions sur ce nouvel état créé pour éviter des
                         * franchissements de régions orthogonales non tolérés
                         * par PlantUML !
                         */
                        state_view += _PlantUML_state + left_name + "_" + right_name + " {\n";
                        String ancestor_name = Clean_up(this.ancestor()._name);
                        for (String transition : java.util.Optional.ofNullable(transition_views.get(ancestor_name)).orElse(java.util.Collections.emptySet())) {
                            if (transition.contains(_PlantUML_transition + left_name)
                                    || transition.contains(_PlantUML_transition_ + left_name)) { // 'Current_date_and_time_displaying -time_out-> Ambient_temperature_displaying''
                                state_view += transition;
                            } else {
                                if (transition.contains(_PlantUML_transition + right_name)
                                        || transition.contains(_PlantUML_transition_ + right_name)) {
                                    state_view += transition;
                                }
                            }
                        }
                        state_view += left + right + "}\n";
                    } else {
                        state_view += left + right;
                    }
                } else {
//                    assert (_left.isAndWith(_right));
                    state_view += left.startsWith(_PlantUML_state) ? left + _PlantUML_orthogonality.Get() + right : right + _PlantUML_orthogonality.Get() + left;
                }
            } else {
                state_view = history_view + input_state_view + output_state_view + _PlantUML_state + state_name + " {\n" + state_view;
                if (_left.isXorWith(_right)) {
                    for (String transition : java.util.Optional.ofNullable(transition_views.remove(state_name)).orElse(java.util.Collections.emptySet())) {
                        state_view += transition;
                    }
                    state_view += left + right + "}\n";
                } else {
//                    assert (_left.isAndWith(_right));
                    if (left.equals("")) {
                        for (String transition : java.util.Optional.ofNullable(transition_views.remove(state_name)).orElse(java.util.Collections.emptySet())) {
                            state_view += transition;
                        }
                        state_view += right + "}\n";
                    } else {
                        if (right.equals("")) { // 'Pong --> Ping'
                            for (String transition : java.util.Optional.ofNullable(transition_views.remove(state_name)).orElse(java.util.Collections.emptySet())) {
                                state_view += transition;
                            }
                            state_view += left + "}\n";
                        } else {
                            // Les transitions localisées dans 'S3' "redescendent" dans 'S31' ou 'S32' car ils sont orthogonaux :
                            StringBuffer left_ = new StringBuffer(left);
                            StringBuffer right_ = new StringBuffer(right);
                            for (String transition : java.util.Optional.ofNullable(transition_views.remove(state_name)).orElse(java.util.Collections.emptySet())) {
                                // Transition 'Programmable_thermostat --> Run' dessinée 2 fois cause 'transition_views.**get**(ancestor_name)' ci-dessus...
                                if (left_.indexOf(transition) == -1 && right_.indexOf(transition) == -1) {
                                    if (transition.startsWith(left_name)) {
                                        left_.insert(left_.lastIndexOf​("}\n"), transition);
                                    } else {
                                        if (transition.startsWith(right_name)) {
                                            right_.insert(right_.lastIndexOf​("}\n"), transition);
                                        } else {
// 'state "Programmable_thermostat→" as Programmable_thermostat.....Program_refreshing'
// 'Programmable_thermostat.....Program_refreshing #magenta  -[#magenta,dashed]-> Program_refreshing : @view_program/Programmable_thermostat.set_no_input(Integer)@-1925558730@'                                   
// JUSTE AVANT : 'state Program_refreshing {'
// 'state "Programmable_thermostat→" as Programmable_thermostat.....Set_program'
// 'Programmable_thermostat.....Set_program #magenta  -[#magenta,dashed]-> Set_program : @view_program/Programmable_thermostat.set_period(Integer)@1528895282@'
// JUSTE AVANT : 'state Set_program {'
                                            int index_of_PlantUML_state_exit_point = transition.indexOf(_PlantUML_state_exit_point);
                                            if (index_of_PlantUML_state_exit_point != -1) {
                                                String to = transition.substring(index_of_PlantUML_state_exit_point + _PlantUML_state_exit_point.length(), transition.indexOf("\n"));
                                                int index = left_.indexOf(_PlantUML_state + to + " {\n");
                                                if (index != -1) {
                                                    left_.insert(index, transition);
                                                } else {
                                                    index = right_.indexOf(_PlantUML_state + to + " {\n");
                                                    if (index != -1) {
                                                        right_.insert(index, transition);
                                                    } else {
                                                        /**
                                                         * Zone peu testée :
                                                         */
// Overriding: '_My_device_state_machine.fires("request_e", _S2, _Idle);'
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            state_view += left.startsWith(_PlantUML_state) ? left_ + _PlantUML_orthogonality.Get() + right_ + "}\n" : right_ + _PlantUML_orthogonality.Get() + left_ + "}\n";
                        }
                    }
                }
            }
        }
        return state_view;
    }

//    /**
//     * For visualization purposes only: Java ME utility method to replace
//     * <CODE>s.split("\n")</CODE>.
//     *
//     * @since ver. 1.3
//     */
//    private static String[] _Split(String s) {
//        if (s == null) {
//            return new String[0];
//        }
//        String[] pieces = new String[]{s};
//        String[] temp;
//        for (int i = 0, index = pieces[i].indexOf("\n"); index != -1; i++, index = pieces[i].indexOf("\n")) {
//            temp = new String[pieces.length + 1];
//            System.arraycopy(pieces, 0, temp, 0, pieces.length);
//            pieces = temp;
//            pieces[i + 1] = pieces[i].substring(index + 1);
//            pieces[i] = pieces[i].substring(0, index);
//        }
//        if (pieces[pieces.length - 1].equals("")) {
//            temp = new String[pieces.length - 1];
//            System.arraycopy(pieces, 0, temp, 0, pieces.length - 1);
//            pieces = temp;
//        }
//        return pieces;
//    }
    private static enum _PlantUML_orthogonality {
        Horizontal_separator() {
            public String toString() {
                return "--\n";
            }
        },
        Vertical_separator {
            public String toString() {
                return "||\n"; // "\\|\\|\n"
            }
        };

        private static String Get() {
            return Math.random() > 0.5D ? Horizontal_separator.toString() : Vertical_separator.toString();
        }
    }
    protected static final String _PlantUML_entry_point = " <<entryPoint>>\n";
    protected static final String _PlantUML_exit_point = " <<exitPoint>>";
    protected static final String _PlantUML_state = "state ";
    protected static final String _PlantUML_state_entry_point = "_____";
    protected static final String _PlantUML_state_exit_point = ".....";
    protected static final String _PlantUML_transition = " --> ";
    protected static final String _PlantUML_transition_ = " -[#magenta,dashed]-> ";
    protected static final String _PlantUML_transition_execution = " : ";
}
