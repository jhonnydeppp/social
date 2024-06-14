package com.jhonny.social.domain.usecases.mapper


import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.mapper.Mapper
import com.jhonny.social.presenter.entities.*

import javax.inject.Inject

class UserMapper @Inject constructor() : Mapper<DomainUser?, UserPresentation>() {

    override fun map(info: DomainUser?): UserPresentation =
        UserPresentation(
            list = info?.list?.map {
                UserItemPresentation(
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

}
