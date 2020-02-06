/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtend.core.tests.compiler

import org.junit.Test

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
class CompilerBug465058Test extends AbstractXtendCompilerTest {
	
	@Test def test_01() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.<String>head]
				}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			import org.eclipse.xtext.xbase.lib.IterableExtensions;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return IterableExtensions.<String>head(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			}
		''')
	}
	
	@Test def test_02() {
		'''
			abstract class Bug {
				val list = newArrayList('a', 'b')
				def void foo(=>Object f)
				def bar() {
					foo[ list.<String>head ]
				}
			}
		'''.assertCompilesTo('''
			import java.util.ArrayList;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			import org.eclipse.xtext.xbase.lib.IterableExtensions;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final ArrayList<String> list = CollectionLiterals.<String>newArrayList("a", "b");
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return IterableExtensions.<String>head(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			}
		''')
	}
	
	@Test def test_03() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head]
				}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			import org.eclipse.xtext.xbase.lib.IterableExtensions;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return IterableExtensions.<String>head(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			}
		''')
	}
	
	@Test def test_04() {
		'''
			abstract class Bug {
				val list = newArrayList('a', 'b')
				def void foo(=>Object f)
				def bar() {
					foo[ list.head ]
				}
			}
		'''.assertCompilesTo('''
			import java.util.ArrayList;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			import org.eclipse.xtext.xbase.lib.IterableExtensions;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final ArrayList<String> list = CollectionLiterals.<String>newArrayList("a", "b");
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return IterableExtensions.<String>head(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			}
		''')
	}
	
	@Test def test_05() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head2]
				}
				def <T> T head2(Iterable<T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final Iterable<T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_06() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head2]
				}
				def <T> T head2(Iterable<? extends T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final Iterable<? extends T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_07() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head2]
				}
				def <T> T head2(T[] iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Conversions;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(((String[])Conversions.unwrapArray(Bug.this.list, String.class)));
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final T[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_08() {
		'''
			abstract class Bug {
				def void foo(=>Object f)
				def bar() {
					foo[head2(#["one", "two", "three"])]
				}
				def <T> T head2(T[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(new String[] { "one", "two", "three" });
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final T[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_09() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[head2(list)]
				}
				def <T> T head2(Iterable<T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final Iterable<T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_10() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head2]
				}
				def <T extends CharSequence> T head2(Iterable<T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends CharSequence> T head2(final Iterable<T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_11() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head2]
				}
				def <T extends CharSequence> T head2(Iterable<? extends T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends CharSequence> T head2(final Iterable<? extends T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_12() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[list.head2]
				}
				def <T extends CharSequence> T head2(T[] iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Conversions;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(((String[])Conversions.unwrapArray(Bug.this.list, String.class)));
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends CharSequence> T head2(final T[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_13() {
		'''
			abstract class Bug {
				def void foo(=>Object f)
				def bar() {
					foo[head2(#["one", "two", "three"])]
				}
				def <T extends CharSequence> T head2(T[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(new String[] { "one", "two", "three" });
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends CharSequence> T head2(final T[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_14() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void foo(=>Object f)
				def bar() {
					foo[head2(list)]
				}
				def <T extends CharSequence> T head2(Iterable<T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends CharSequence> T head2(final Iterable<T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_15() {
		'''
			abstract class Bug {
				val list = #["one", "two", "three"]
				def void bar() {
					var =>Object x = [head2(list)]
					x.apply
				}
				def <T extends CharSequence> T head2(Iterable<T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import java.util.List;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  private final List<String> list = Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three"));
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(Bug.this.list);
			      }
			    };
			    Function0<?> x = _function;
			    x.apply();
			  }
			  
			  public <T extends CharSequence> T head2(final Iterable<T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_16() {
		'''
			abstract class Bug {
				def bar() {
					var =>Object x = [head2(#["one", "two", "three"])]
					x.apply
				}
				def <T extends CharSequence> T head2(Iterable<T> iterable) {}
			}
		'''.assertCompilesTo('''
			import java.util.Collections;
			import org.eclipse.xtext.xbase.lib.CollectionLiterals;
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public Object bar() {
			    Object _xblockexpression = null;
			    {
			      final Function0<Object> _function = new Function0<Object>() {
			        public Object apply() {
			          return Bug.this.<String>head2(Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList("one", "two", "three")));
			        }
			      };
			      Function0<?> x = _function;
			      _xblockexpression = x.apply();
			    }
			    return _xblockexpression;
			  }
			  
			  public <T extends CharSequence> T head2(final Iterable<T> iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_17() {
		'''
			abstract class Bug {
				def bar() {
					var =>Object x = [head2(#["one", "two", "three"])]
					x.apply()
				}
				def <T extends CharSequence> T head2(T[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public Object bar() {
			    Object _xblockexpression = null;
			    {
			      final Function0<Object> _function = new Function0<Object>() {
			        public Object apply() {
			          return Bug.this.<String>head2(new String[] { "one", "two", "three" });
			        }
			      };
			      Function0<?> x = _function;
			      _xblockexpression = x.apply();
			    }
			    return _xblockexpression;
			  }
			  
			  public <T extends CharSequence> T head2(final T[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_18() {
		'''
			abstract class Bug {
				def void foo(=>Object f)
				def bar() {
					foo[head2(#[1, 1.0])]
				}
				def <T> T head2(T[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return ((Number)Bug.this.<Number>head2(new Number[] { Integer.valueOf(1), Double.valueOf(1.0) }));
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final T[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_19() {
		'''
			abstract class Bug {
				def void foo(=>Object f)
				def bar() {
					foo[head2(#['', ''])]
				}
				def <T> T head2(Comparable<T>[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return Bug.this.<String>head2(new Comparable[] { "", "" });
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Object> T head2(final Comparable<T>[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_20() {
		'''
			abstract class Bug {
				def void foo(=>Object f)
				def bar() {
					foo[head2(#[null as Integer, null as Double])]
				}
				def <T extends Number> T head2(Comparable<? extends T>[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return ((Number)Bug.this.<Number>head2(new Comparable[] { ((Integer) null), ((Double) null) }));
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Number> T head2(final Comparable<? extends T>[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
	@Test def test_21() {
		'''
			abstract class Bug {
				def void foo(=>Object f)
				def bar() {
					foo[head2(#[1, 1.0])]
				}
				def <T extends Number> T head2(Comparable<? extends T>[] iterable) {}
			}
		'''.assertCompilesTo('''
			import org.eclipse.xtext.xbase.lib.Functions.Function0;
			
			@SuppressWarnings("all")
			public abstract class Bug {
			  public abstract void foo(final Function0<?> f);
			  
			  public void bar() {
			    final Function0<Object> _function = new Function0<Object>() {
			      public Object apply() {
			        return ((Number)Bug.this.<Number>head2(new Comparable[] { Integer.valueOf(1), Double.valueOf(1.0) }));
			      }
			    };
			    this.foo(_function);
			  }
			  
			  public <T extends Number> T head2(final Comparable<? extends T>[] iterable) {
			    return null;
			  }
			}
		''')
	}
	
}