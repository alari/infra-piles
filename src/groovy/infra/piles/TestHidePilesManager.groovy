package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:32 AM
 */
class TestHidePilesManager extends TestPilesManager implements HidePilesManager<TestItem,TestPile> {
    @Override
    void hide(TestItem item, TestPile pile) {
        item.piles.remove(pile)
        item.hidden.add(pile)
        pile.plainItems.remove(item)
    }

    @Override
    boolean isHidden(TestItem item, TestPile pile) {
        item.hidden.contains(pile)
    }

    @Override
    void reveal(TestItem item, TestPile pile) {
        item.piles.add(pile)
        item.hidden.remove(pile)
        pile.plainItems.push(item)
    }

    @Override
    List<TestPile> getPiles(TestItem item) {
        item.piles.toList() + item.hidden.toList()
    }

    boolean inPile(TestItem item, TestPile pile) {
        item.piles.contains(pile) || item.hidden.contains(pile)
    }
}
