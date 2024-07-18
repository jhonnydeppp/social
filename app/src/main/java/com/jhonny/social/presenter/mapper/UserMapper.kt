package com.jhonny.social.presenter.mapper


import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.DomainUserItem
import com.jhonny.social.domain.mapper.Mapper
import com.jhonny.social.presenter.entities.Dob
import com.jhonny.social.presenter.entities.Location
import com.jhonny.social.presenter.entities.Name
import com.jhonny.social.presenter.entities.Picture
import com.jhonny.social.presenter.entities.Street
import com.jhonny.social.presenter.entities.UserItemPresentation
import com.jhonny.social.presenter.entities.UserPresentation
import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<DomainUser?, UserPresentation>() {

    override fun map(info: DomainUser?): UserPresentation =
        UserPresentation(
            list = info?.list?.map {
                UserItemPresentation(
                    isFavorite = it?.isFavorite?: false,
                    nat = it?.nat,
                    gender = it?.gender,
                    phone = it?.phone,
                    dob = Dob(
                        date = it?.dob?.date,
                        age = it?.dob?.age
                    ),
                    picture = Picture(
                        thumbnail = it?.picture?.thumbnail,
                        large = it?.picture?.large,
                        medium = it?.picture?.medium
                    ),
                    name = Name(
                        last = it?.name?.last,
                        first = it?.name?.first,
                        title = it?.name?.title
                    ),
                    location = Location(
                        country = it?.location?.country,
                        city = it?.location?.city,
                        street = Street(
                            number = it?.location?.street?.number,
                            name = it?.location?.street?.name
                        ),
                        postcode = it?.location?.postcode,
                    ),
                    cell = it?.cell,
                    email = it?.email
                )
            } ?: emptyList()
        )


    fun mapToDomain(info: UserPresentation?): DomainUser =
        DomainUser(
            list = info?.list?.map {
                DomainUserItem(
                    isFavorite = it?.isFavorite?: false,
                    nat = it?.nat,
                    gender = it?.gender,
                    phone = it?.phone,
                    dob = com.jhonny.social.domain.entities.Dob(
                        date = it?.dob?.date,
                        age = it?.dob?.age
                    ),
                    picture = com.jhonny.social.domain.entities.Picture(
                        thumbnail = it?.picture?.thumbnail,
                        large = it?.picture?.large,
                        medium = it?.picture?.medium
                    ),
                    name = Name(
                        last = it?.name?.last,
                        first = it?.name?.first,
                        title = it?.name?.title
                    ),
                    location = com.jhonny.social.domain.entities.Location(
                        country = it?.location?.country,
                        city = it?.location?.city,
                        street = com.jhonny.social.domain.entities.Street(
                            number = it?.location?.street?.number,
                            name = it?.location?.street?.name
                        ),
                        postcode = it?.location?.postcode,
                    ),
                    cell = it?.cell,
                    email = it?.email
                )
            } ?: emptyList()
        )
}
