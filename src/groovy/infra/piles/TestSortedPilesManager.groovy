package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:33 AM
 */
class TestSortedPilesManager implements SortedPilesManager<TestItem,TestPile> {
    @Override
    void put(TestItem item, TestPile pile, int position) {
        pile.sortedItems.remove(item)
        position <= pile.sortedItems.size() ? pile.sortedItems.add(position, item) : pile.sortedItems.add(item)
        item.sorted.add(pile)
    }

    @Override
    Collection<TestItem> remove(TestItem item, TestPile pile, boolean withTail) {
        if (withTail) {
            int index = pile.sortedItems.indexOf(item)
            if (index >= 0) {
                Collection<TestItem> itemsToRemove = []
                for (int i=index; i<pile.sortedItems.size(); i++){
                    itemsToRemove.add pile.sortedItems[i]
                }
                itemsToRemove.each {
                    it.sorted.remove(pile)
                    pile.sortedItems.remove(it)
                }
                return itemsToRemove
            }
        } else {
            pile.sortedItems.remove(pile)
        }
        item.sorted.remove(pile)
        return [item]
    }

    @Override
    void empty(TestPile pile) {
        pile.sortedItems.each {
            it.sorted.remove(pile)
        }
        pile.sortedItems = []
    }

    @Override
    void put(TestItem item, TestPile pile) {
        assert item
        assert pile
        if (!pile.sortedItems.contains(item)) pile.sortedItems.add(item)
        assert item.sorted != null
        item.sorted.add(pile)
    }

    @Override
    void remove(TestItem item, TestPile pile) {
        pile.sortedItems.remove(item)
        item.sorted.remove(pile)
    }

    @Override
    List<TestItem> draw(TestPile pile, long limit, long offset) {
        List<TestItem> items = []
        if (limit) {
            items.addAll pile.sortedItems.subList((int)offset, Math.min((int)offset+limit, pile.sortedItems.size()))
        } else {
            items.addAll pile.sortedItems
        }
        items
    }

    @Override
    Collection<TestPile> getPiles(TestItem item) {
        item.sorted
    }

    @Override
    boolean inPile(TestItem item, TestPile pile) {
        pile.sortedItems.contains(item)
    }

    @Override
    long sizeOf(TestPile pile) {
        pile.sortedItems.size()
    }
}
