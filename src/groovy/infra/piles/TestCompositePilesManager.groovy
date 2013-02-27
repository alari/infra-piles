package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:34 AM
 */
class TestCompositePilesManager implements CompositePilesManager<TestItem,TestPile> {
    SortedPilesManager<TestItem, TestPile> sortedManager = new TestSortedPilesManager()
    HidePilesManager<TestItem,TestPile> plainManager = new TestHidePilesManager()

    @Override
    void release(TestItem item, TestPile pile, boolean withTail) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    List<TestItem> drawSorted(TestPile pile, long limit, long offset) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void put(TestItem item, TestPile pile, int position) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void remove(TestItem item, TestPile pile, boolean withTail) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void put(TestItem item, TestPile pile) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    void remove(TestItem item, TestPile pile) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    List<TestItem> draw(TestPile pile, long limit, long offset) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    Collection<TestPile> getPiles(TestItem item) {
        return null  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    boolean inPile(TestItem item, TestPile pile) {
        return false  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    long sizeOf(TestPile pile) {
        return 0  //To change body of implemented methods use File | Settings | File Templates.
    }
}
