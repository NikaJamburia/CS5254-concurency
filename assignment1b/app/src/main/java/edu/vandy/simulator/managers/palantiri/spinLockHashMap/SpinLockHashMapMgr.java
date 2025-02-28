package edu.vandy.simulator.managers.palantiri.spinLockHashMap;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Semaphore;

import edu.vandy.simulator.managers.palantiri.Palantir;
import edu.vandy.simulator.managers.palantiri.PalantiriManager;
import edu.vandy.simulator.utils.Assignment;

/**
 * A PalantiriManager implemented using a SpinLock, a Semaphore, and a
 * HashMap.
 */
public class SpinLockHashMapMgr extends PalantiriManager {
    /**
     * Debugging tag used by the Android logger.
     */
    protected final static String TAG =
            SpinLockHashMapMgr.class.getSimpleName();

    /**
     * A map that associates the @a Palantiri key to the @a boolean
     * values that keep track of whether the key is available.
     */
    private Map<Palantir, Boolean> mPalantiriMap;

    /**
     * A "cancellable (spin) lock" used to ensure that threads
     * serialize on a critical section.
     */
    // TODO -- you fill in here.
    private CancellableLock lock;
    

    /**
     * A counting Semaphore that limits concurrent access to the fixed
     * number of available palantiri managed by the PalantiriManager.
     */
    // TODO -- you fill in here.
    private Semaphore semaphore;
    

    /**
     * Called to allow subclass implementations the opportunity
     * to setup fields and initialize field values.
     */
    @Override
    protected void buildModel() {
        // Create a new HashMap.
        mPalantiriMap = new HashMap<>();

        // Initialize the Semaphore to use a "fair" implementation
        // that mediates concurrent access to the given Palantiri.
        // TODO -- you fill in here.
        semaphore = new Semaphore(2, true);


        if (Assignment.isUndergraduate()) {
            // UNDERGRADUATES:
            //
            // NOTE: You must set the assignment type to Undergraduate
            // in edu.vandy.simulator.utils.Student.kt.
            //
            // Initialize the CancellableLock by replacing the null
            // value with your SpinLock implementation.

            // TODO -- you fill in here.
            

            // Iterate through the List of Palantiri returned via the
            // getPalantiri() factory method and initialize each key
            // in the mPalantiriMap with "true" to indicate it's
            // available.
            // TODO -- you fill in here.
            
        } else if (Assignment.isGraduate()) {
            // GRADUATES:
            //
            // NOTE: You must set the assignment type to Graduate in
            // edu.vandy.simulator.utils.Student.kt.
            // 
            // Initialize the CancellableLock by replacing the
            // null value with your ReentrantSpinLock implementation.

            // TODO -- you fill in here.
            lock = new ReentrantSpinLock();


            // Use the List.forEach() method to iterate through the
            // List of Palantiri returned via the getPalantiri()
            // factory method and initialize each key in the
            // mPalantiriMap with "true" to indicate it's available.
            // TODO -- you fill in here.
            getPalantiri().forEach(p -> mPalantiriMap.put(p, true));

            
        } else {
            throw new IllegalStateException("Invalid assignment type");
        }
    }

    /**
     * Get a Palantir from the PalantiriManager, blocking until one is
     * available.
     *
     * This method should never return a null Palantir. It may,
     * however, throw a {@link InterruptedException} if a shutdown is
     * being processed while a thread is waiting for a Palantir.
     *
     * @return The first available Palantir
     * @throws {@link InterruptedException} if interrupted
     */
    @Override
    @NotNull
    protected Palantir acquire() throws InterruptedException {
        // Acquire the Semaphore interruptibly and then acquired the
        // spin-lock to ensure that finding the first key in the
        // HashMap whose value is "true" (which indicates it's
        // available for use) occurs in a thread-safe manner.  Replace
        // the value of this key with "false" to indicate the Palantir
        // isn't available, return that palantir to the client, and
        // release the spin-lock in a manner that is robust to
        // exceptions.

        // TODO -- you fill in here.
        semaphore.acquire();
        lock.lock(() -> false);
        try {
            var palantirEntry = getFirstAvailablePalantir();
            while (palantirEntry.isEmpty()) {
                palantirEntry = getFirstAvailablePalantir();
            }

            var palantir = palantirEntry.get().getKey();
            mPalantiriMap.put(palantir, false);
            return palantir;
        } finally {
            lock.unlock();
        }

    }

    @NonNull
    private Optional<Map.Entry<Palantir, Boolean>> getFirstAvailablePalantir() {
        return mPalantiriMap
                .entrySet()
                .stream()
                .filter(Map.Entry::getValue).findFirst();
    }

    /**
     * Releases the {@code palantir} so that it's available for other
     * Beings to use.
     *
     * @param palantir The palantir to release to the Palantir pool
     * @throws {@link IllegalArgumentException} if the {@code
     *         palantir} is invalid
     */
    @Override
    protected void release(Palantir palantir) {
        // Update the status of the designated palantir in the
        // SpinLockHashMapMgr so it's available for other Beings to
        // use. This method should efficiently and robustly
        // 1. Handle invalid palantir values, such as null values or
        //    values that are not valid keys in the HashMap.
        // 2. Lock and unlock the spin lock field correctly to ensure
        //    mutually exclusive access to mutable shared state.
        // 3. Only release the semaphore if the palantir parameter
        //    is correct.
        // TODO -- you fill in here.
        lock.lock(() -> false);
        try {
            if (mPalantiriMap.replace(palantir, true) == null) {
                throw new IllegalArgumentException("Palentir invalid!");
            }
        } finally {
            lock.unlock();
            semaphore.release();
        }
        
    }

    /**
     * Called when the simulation is being shutdown to allow model
     * components the opportunity to and release resources and to
     * reset field values.
     */
    @Override
    public void shutdownNow() {
    }
}
