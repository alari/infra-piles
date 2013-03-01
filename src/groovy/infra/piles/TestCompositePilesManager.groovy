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
        if (sortedManager.inPile(item, pile)) {
            sortedManager.remove(item, pile, withTail).each {
                plainManager.put(item, pile)
            }
        }
    }

    @Override
    List<TestItem> drawSorted(TestPile pile, long limit, long offset) {
        sortedManager.draw(pile, limit, offset)
    }

    @Override
    void put(TestItem item, TestPile pile, int position) {
        int sortedSize = sortedManager.sizeOf(pile)
        plainManager.remove(item, pile)
        if (position <= sortedSize) {
            sortedManager.put(item, pile, position)
            return;
        }
        plainManager.draw(pile, position-sortedSize-1, 0).each {
            sortedManager.put(it, pile)
        }
        sortedManager.put(item, pile)
    }

    @Override
    void put(TestItem item, TestPile pile) {
        if (!inPile(item, pile)) {
            plainManager.put(item, pile)
        }
    }

    @Override
    Collection<TestItem> remove(TestItem item, TestPile pile, boolean withTail) {
        plainManager.remove(item, pile)
        if (sortedManager.inPile(item, pile)) {
            sortedManager.remove(item, pile, withTail)
        } else {
            [item]
        }
    }

    @Override
    void remove(TestItem item, TestPile pile) {
        plainManager.remove(item, pile)
        sortedManager.remove(item, pile)
    }

    @Override
    List<TestItem> draw(TestPile pile, long limit, long offset) {
        List<TestItem> items = drawSorted(pile, limit, offset)
        if (!limit || items.size() < limit) {
            items.addAll(plainManager.draw(pile, limit ? limit-items.size() : 0, offset > items.size() ? offset - items.size() : 0))
        }
        items
    }

    @Override
    Collection<TestPile> getPiles(TestItem item) {
        (plainManager.getPiles(item) + sortedManager.getPiles(item)).unique()
    }

    @Override
    boolean inPile(TestItem item, TestPile pile) {
        plainManager.inPile(item, pile) || sortedManager.inPile(item,pile)
    }

    @Override
    long sizeOf(TestPile pile) {
        plainManager.sizeOf(pile) + sortedManager.sizeOf(pile)
    }
}
