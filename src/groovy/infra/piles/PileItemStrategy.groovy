package infra.piles

import infra.ca.Atom
import infra.ca.AtomPush
import infra.ca.strategy.AtomStrategy
import org.springframework.stereotype.Component

/**
 * @author alari
 * @since 3/28/13 11:48 PM
 */
@Component
class PileItemStrategy extends AtomStrategy {
    @Override
    boolean isContentSupported(AtomPush data) {
        data.type == getName() && data.externalId
    }

    @Override
    void setContent(Atom atom, AtomPush data) {
        atom.externalId = data.externalId
    }
}
