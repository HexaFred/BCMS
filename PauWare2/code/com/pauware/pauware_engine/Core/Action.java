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

import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

import com.pauware.pauware_engine.Exceptions.State_exception;

/**
 * This concrete class represents the general notion of <b>Action</b> in UML.
 * <p>
 * Compatibility: <I>Java 9</I>.
 *
 * @author Franck.Barbier@FranckBarbier.com
 * @version 2.0 (Sept. 2021)
 * @since 1.0
 */
public class Action extends AbstractAction {

    /**
     * @param object
     * @param action
     * @param args
     * @see AbstractAction#AbstractAction(Object,String,Object[])
     */
    protected Action(Object object, String action, Object[] args) {
        super(object, action, args);
    }

    /**
     * This method is called during a run-to-completion cycle and executes the
     * action instance by <b>reflection</b>; Actions may be re-executed.
     * <p>
     * This method is called automatically, and more precisely, at an
     * appropriate moment with respect to the functioning of a state machine.
     * All kinds of probable exceptions including Java <CODE>Error</CODE>
     * instances and runtime exceptions are caught by this method. The computed
     * result is assigned to the <CODE>_result</CODE> variable and is made
     * readable through the {@link AbstractAction#verbose()} method.
     */
    @Override
    public void execute() throws State_exception {
        if (_action != null) {
            Class<?>[] args_types = null;
            if (_args != null) {
                if (_args.length > 0) {
                    args_types = new Class<?>[_args.length];
                    for (int i = 0; i < _args.length; i++) {
                        /**
                         * Bug corrected in Nov. 2013:
                         */
                        if (_args[i] == null) {
                            args_types[i] = Object.class;
                        } else {
                            args_types[i] = _args[i].getClass();
                        }
                        /**
                         * End of bug
                         */
                    }
                }
            }
            Object object = _object;
            try {
                Method method;
                if (object != null) {
                    if (object instanceof java.util.Collection) {
                        object = ((java.util.Collection) object).toArray();
                    }
                    if (object instanceof Object[]) {
                        if (((Object[]) object).length > 0) {
                            method = ((Object[]) object)[0].getClass().getMethod(_action, args_types);
                            try {
                                for (int i = 0; i < ((Object[]) object).length; i++) {
                                    _result = method.invoke(((Object[]) object)[i], _args);
                                }
                            } catch (IllegalAccessException iae) {
                                _result = object.toString() + "." + _action + " failed: " + iae.toString();
                                throw new State_exception(_result.toString());
                            } catch (IllegalArgumentException iae) {
                                _result = object.toString() + "." + _action + " failed: " + iae.toString();
                                throw new State_exception(_result.toString());
                            } catch (InvocationTargetException ite) {
                                if (ite.getCause() != null) {
                                    _result = object.toString() + "." + _action + " failed: " + ite.getCause().toString();
                                } else {
                                    _result = object.toString() + "." + _action + " failed: " + ite.toString();
                                }
                                throw new State_exception(_result.toString());
                            } catch (NullPointerException npe) {
                                _result = object.toString() + "." + _action + " failed: " + npe.toString();
                                throw new State_exception(_result.toString());
                            } catch (ExceptionInInitializerError eiie) {
                                _result = object.toString() + "." + _action + " failed: " + eiie.toString();
                                throw new State_exception(_result.toString());
                            }
                        }
                    } else {
                        method = object.getClass().getMethod(_action, args_types);
                        try {
                            _result = method.invoke(object, _args);
                        } catch (IllegalAccessException iae) {
                            _result = object.toString() + "." + _action + " failed: " + iae.toString();
                            throw new State_exception(_result.toString());
                        } catch (IllegalArgumentException iae) {
                            _result = object.toString() + "." + _action + " failed: " + iae.toString();
                            throw new State_exception(_result.toString());
                        } catch (InvocationTargetException ite) {
                            if (ite.getCause() != null) {
                                _result = object.toString() + "." + _action + " failed: " + ite.getCause().toString();
                            } else {
                                _result = object.toString() + "." + _action + " failed: " + ite.toString();
                            }
                            throw new State_exception(_result.toString());
                        } catch (NullPointerException npe) {
                            _result = object.toString() + "." + _action + " failed: " + npe.toString();
                            throw new State_exception(_result.toString());
                        } catch (ExceptionInInitializerError eiie) {
                            _result = object.toString() + "." + _action + " failed: " + eiie.toString();
                            throw new State_exception(_result.toString());
                        }
                    }
                }
            } catch (NoSuchMethodException nsme) {
                if (args_types != null) {
                    Method[] methods;
                    if (object instanceof Object[]) {
                        methods = ((Object[]) object)[0].getClass().getMethods();
                    } else {
                        methods = object.getClass().getMethods();
                    }
                    int i = 0;
                    boolean invoke = false;
                    while (i < methods.length && !invoke) {
                        if (methods[i].getName().equals(_action)) {
                            Class<?>[] parameter_types = methods[i].getParameterTypes();
                            if (args_types.length == parameter_types.length) {
                                boolean conformity = true;
                                for (int j = 0; j < args_types.length; j++) {
                                    if (args_types[j] != parameter_types[j]) { // added April 2005
                                        Class<?> interfaces[] = args_types[j].getInterfaces();
                                        int k = 0;
                                        boolean stop = false;
                                        while (k < interfaces.length && !stop) {
                                            if (interfaces[k] == parameter_types[j]) {
                                                stop = true;
                                            }
                                            k++;
                                        }
                                        conformity = conformity && stop;
                                    }
                                }
                                invoke = conformity;
                            }
                        }
                        i++;
                    }
                    try {
                        if (invoke) {
                            if (object instanceof Object[]) {
                                for (int j = 0; j < ((Object[]) object).length; j++) {
                                    _result = methods[i - 1].invoke(((Object[]) object)[j], _args);
                                }
                            } else {
                                _result = methods[i - 1].invoke(object, _args);
                            }
                        } else {
                            _result = object.toString() + "." + _action + " failed: " + nsme.toString();
                            throw new State_exception(_result.toString());
                        }
                    } catch (IllegalAccessException iae) {
                        _result = object.toString() + "." + _action + " failed: " + iae.toString();
                        throw new State_exception(_result.toString());
                    } catch (IllegalArgumentException iae) {
                        _result = object.toString() + "." + _action + " failed: " + iae.toString();
                        throw new State_exception(_result.toString());
                    } catch (InvocationTargetException ite) {
                        if (ite.getCause() != null) {
                            _result = object.toString() + "." + _action + " failed: " + ite.getCause().toString();
                        } else {
                            _result = object.toString() + "." + _action + " failed: " + ite.toString();
                        }
                        throw new State_exception(_result.toString());
                    } catch (NullPointerException npe) {
                        _result = object.toString() + "." + _action + " failed: " + npe.toString();
                        throw new State_exception(_result.toString());
                    } catch (ExceptionInInitializerError eiie) {
                        _result = object.toString() + "." + _action + " failed: " + eiie.toString();
                        throw new State_exception(_result.toString());
                    }
                } else {
                    _result = object.toString() + "." + _action + " failed: " + nsme.toString();
                    throw new State_exception(_result.toString());
                }
            } catch (SecurityException se) {
                _result = object.toString() + "." + _action + " failed: " + se.toString();
                throw new State_exception(_result.toString());
            }
        }
    }

    /**
     * This method allows the possibility of waiting for actions to complete.
     * Since UML actions are recognized as instantaneous, this method is left
     * empty.
     *
     * @see Do_activity#wait_for_completion()
     */
    @Override
    protected void wait_for_completion() throws State_exception {
    }
}
