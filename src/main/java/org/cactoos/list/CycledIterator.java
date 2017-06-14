/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.cactoos.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Cycled Iterator.
 *
 * @author Ilia Rogozhin (ilia.rogozhin@gmail.com)
 * @version $Id$
 * @param <T> Type of item
 * @since 0.6
 */
public final class CycledIterator<T> implements Iterator<T> {

    /**
     * Iterable.
     */
    private final Iterable<T> iterable;

    /**
     * Iterator.
     */
    private Iterator<T> iterator;

    /**
     * Ctor.
     * @param iterable Iterable.
     */
    public CycledIterator(final Iterable<T> iterable) {
        this.iterable = iterable;
        this.iterator = this.iterable.iterator();
    }

    @Override
    public boolean hasNext() {
        return this.iterator.hasNext() || this.iterable.iterator().hasNext();
    }

    @Override
    public T next() {
        if (!this.iterator.hasNext()) {
            this.iterator = this.iterable.iterator();
            if (!this.iterator.hasNext()) {
                throw new NoSuchElementException();
            }
        }
        return this.iterator.next();
    }
}
